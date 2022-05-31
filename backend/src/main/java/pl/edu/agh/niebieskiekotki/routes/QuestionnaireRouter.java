package pl.edu.agh.niebieskiekotki.routes;

import org.apache.commons.codec.digest.DigestUtils;
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
    List<Questionnaire> questionnaires = new ArrayList<>();

    public QuestionnaireRouter(HibernateAdapter hibernateAdapter, EmailService emailService) {
        this.hibernateAdapter = hibernateAdapter;
        this.emailService = emailService;
    }
    @Transactional
    @GetMapping(value = "/questionnaires")
    public List<Questionnaire> GetAll(@RequestHeader("Auth-Token") String token) throws UnauthorizedException {
        Teacher teacher = AuthRoute.getTeacherFromToken(token, hibernateAdapter);
        System.out.println(teacher.getId());
        return hibernateAdapter.getWhereEq(Questionnaire.class, "teacher", teacher);
    }
    @Transactional
    @GetMapping(value = "/questionnaires/{id}")
    public QuestionnaireDetail GetOne(@RequestHeader("Auth-Token") String token, @PathVariable Long id) throws NotFoundException, UnauthorizedException {
        Teacher teacher = AuthRoute.getTeacherFromToken(token, hibernateAdapter);
        Questionnaire questionnaire = hibernateAdapter.getById(Questionnaire.class, id);

        if (questionnaire == null)
            throw new NotFoundException("Not found questionnaire with id:" + id);
        if(!questionnaire.getTeacher().equals(teacher))
            throw new UnauthorizedException("Its not your questionnaire");

        return new QuestionnaireDetail(questionnaire);
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
            emailService.sendToAll(studentsWithLinks);
        }

        return new QuestionnaireDetail(newQuestionnaire);
    }

    @PutMapping(value = "/questionnaires/{id}")
    public Questionnaire Put(@PathVariable Long id, @RequestBody Questionnaire questionnaire) throws Exception {

        System.out.println("Enter put");

        Questionnaire toReturn = questionnaires
                .stream()
                .filter(q -> q.getId() != null && q.getId().equals(id))
                .findFirst()
                .orElse(null);
        System.out.println(toReturn);

        if (toReturn == null)
            throw new NotFoundException("Not found questionnaire with id:" + id);

        toReturn.setLabel(questionnaire.getLabel());

        return toReturn;
    }

    @DeleteMapping(value = "/questionnaires/{id}")
    public Questionnaire Delete(@PathVariable Long id) throws Exception {

        Questionnaire toReturn = questionnaires
                .stream()
                .filter(q -> q.getId() != null && q.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (toReturn == null)
            throw new NotFoundException("questionnaire with id=" + id);

        questionnaires.remove(toReturn);
        return toReturn;
    }

}
