package pl.edu.agh.niebieskiekotki.routes;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.niebieskiekotki.HibernateAdapter;
import pl.edu.agh.niebieskiekotki.entitites.*;
import pl.edu.agh.niebieskiekotki.errorsHandling.exceptions.NotFoundException;
import pl.edu.agh.niebieskiekotki.errorsHandling.exceptions.UnauthorizedException;
import pl.edu.agh.niebieskiekotki.utility.EmailService;
import pl.edu.agh.niebieskiekotki.views.AddQuestionnaireView;
import pl.edu.agh.niebieskiekotki.views.QuestionnaireDetail;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController

public class QuestionnaireRouter {

    private final HibernateAdapter hibernateAdapter;
    private final EmailService emailService;

    @Value("${app.serverPath}")
    private String serverPath;

    public QuestionnaireRouter(HibernateAdapter hibernateAdapter, EmailService emailService) {
        this.hibernateAdapter = hibernateAdapter;
        this.emailService = emailService;
    }
    @Transactional
    @GetMapping(value = "/questionnaires")
    public List<Questionnaire> GetAll(@RequestHeader("Auth-Token") String token) throws UnauthorizedException {
        System.out.println(serverPath);
        Teacher teacher = AuthRoute.getTeacherFromToken(token, hibernateAdapter);
        System.out.println(teacher.getId());
        return hibernateAdapter.getWhereEq(Questionnaire.class, "teacher", teacher);
    }
    @Transactional
    @GetMapping(value = "/questionnaires/{id}")
    public QuestionnaireDetail GetOne(@RequestHeader("Auth-Token") String token, @PathVariable Long id) throws NotFoundException, UnauthorizedException {
        Questionnaire questionnaire = getTeachersQuestionnaire(token, id);
        return new QuestionnaireDetail(questionnaire);
    }

    @Transactional
    @DeleteMapping(value = "/questionnaires/{id}")
    public void deleteOne(@RequestHeader("Auth-Token") String token, @PathVariable Long id) throws NotFoundException, UnauthorizedException {
        Questionnaire questionnaire = getTeachersQuestionnaire(token, id);

        if (hibernateAdapter.getWhereEq(Results.class, "questionnaire", id).size() > 0)
            hibernateAdapter.clearResultsWhere(id);

        hibernateAdapter.delete(questionnaire);
    }

    private Questionnaire getTeachersQuestionnaire(String token, Long id) throws UnauthorizedException, NotFoundException {
        Teacher teacher = AuthRoute.getTeacherFromToken(token, hibernateAdapter);
        Questionnaire questionnaire = hibernateAdapter.getById(Questionnaire.class, id);

        if (questionnaire == null)
            throw new NotFoundException("Not found questionnaire with id:" + id);
        if(!questionnaire.getTeacher().equals(teacher))
            throw new UnauthorizedException("Its not your questionnaire");

        return questionnaire;
    }

    @Transactional
    @DeleteMapping(value = "/questionnaires")
    public void GetOne() throws NotFoundException {
        hibernateAdapter.clearDatabase();
    }

    @Transactional
    @PostMapping(value = "/questionnaires")
    public QuestionnaireDetail Post(@RequestHeader("Auth-Token") String token, @RequestBody AddQuestionnaireView addQuestionnaireView) throws UnauthorizedException {

        Teacher teacher = AuthRoute.getTeacherFromToken(token, hibernateAdapter);

        Questionnaire newQuestionnaire = new Questionnaire();

        if (addQuestionnaireView.getTeacherId() == null) addQuestionnaireView.setTeacherId(1L);

        newQuestionnaire.setExpirationDate(addQuestionnaireView.getExpirationDate());
        newQuestionnaire.setLabel(addQuestionnaireView.getLabel());
        newQuestionnaire.setTeacher(teacher);
        newQuestionnaire.setQuestionnaireTerms(new ArrayList<>());
        newQuestionnaire.setQuestionnaireAccesses(new ArrayList<>());


        hibernateAdapter.save(newQuestionnaire);

        List<Term> allTerms = hibernateAdapter.getAll(Term.class);

        for (Term term : allTerms) {
            if (addQuestionnaireView.getAvailableTerms().contains(term.getId())) {
                QuestionnaireTerm qt = new QuestionnaireTerm(newQuestionnaire, term);
                hibernateAdapter.save(qt);
                newQuestionnaire.getQuestionnaireTerms().add(qt);
            }
        }
        for (Student studentInfo : addQuestionnaireView.getStudentsInfo()) {
            Student student = hibernateAdapter.getOneWhereEq(Student.class, "indexNumber", studentInfo.getIndexNumber());
            if (student == null) {
                hibernateAdapter.save(studentInfo);
                student = studentInfo;
            }
            String sha256hex = DigestUtils.sha256Hex(newQuestionnaire.getId() + ":" + student.getIndexNumber());
            QuestionnaireAccess questionnaireAccess = new QuestionnaireAccess(student, newQuestionnaire, sha256hex);
            hibernateAdapter.save(questionnaireAccess);
            newQuestionnaire.questionnaireAccesses.add(questionnaireAccess);
        }
        if (addQuestionnaireView.isAutoSendingLinks()) {
            Map<Student, String> studentsWithLinks = newQuestionnaire.studentsWithLinks();
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    emailService.sendToAll(studentsWithLinks);
                }
            });
            thread.start();
        }

        return new QuestionnaireDetail(newQuestionnaire);
    }
}
