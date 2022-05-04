package pl.edu.agh.niebieskiekotki.routes;

import org.springframework.web.bind.annotation.*;
import pl.edu.agh.niebieskiekotki.HibernateAdapter;
import pl.edu.agh.niebieskiekotki.entitites.Questionnaire;
import pl.edu.agh.niebieskiekotki.entitites.QuestionnaireTerm;
import pl.edu.agh.niebieskiekotki.entitites.Term;
import pl.edu.agh.niebieskiekotki.errorsHandling.exceptions.NotFoundException;
import pl.edu.agh.niebieskiekotki.views.AddQuestionnaireView;
import pl.edu.agh.niebieskiekotki.views.QuestionnaireDetail;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class QuestionnaireRouter {

    private final HibernateAdapter hibernateAdapter;
    List<Questionnaire> questionnaires = new ArrayList<>();

    public QuestionnaireRouter(HibernateAdapter hibernateAdapter) {
        this.hibernateAdapter = hibernateAdapter;
    }

    @GetMapping(value = "/questionnaires")
    public List<Questionnaire> GetAll() {
        return hibernateAdapter.getAll(Questionnaire.class);
    }

    @GetMapping(value = "/questionnaires/{id}")
    public QuestionnaireDetail GetOne(@PathVariable Long id) throws NotFoundException {

        Questionnaire toReturn = hibernateAdapter.getById(Questionnaire.class, id);

        if (toReturn == null)
            throw new NotFoundException("Not found questionnaire with id:" + id);

        return new QuestionnaireDetail(toReturn);
    }

    @GetMapping(value = "/fileWithLinks/{id}")
    public Questionnaire GetFile(@PathVariable Long id) throws NotFoundException, ParserConfigurationException, TransformerException {

        Questionnaire questionnaire = hibernateAdapter.getById(Questionnaire.class, id);

        if (questionnaire == null)
            throw new NotFoundException("Not found questionnaire with id:" + id);
        //questionnaire.questionnaireAccesses.forEach(System.out::println);
        FileWithLinksCreator.createFileWithLinks(questionnaire, Language.ENGLISH);
        return questionnaire;
    }

    @PostMapping(value = "/questionnaires")
    public QuestionnaireDetail Post(@RequestBody AddQuestionnaireView addQuestionnaireView) {

        Questionnaire newQuestionnaire = new Questionnaire();

        if (addQuestionnaireView.getTeacherId() == null) addQuestionnaireView.setTeacherId(1L);

        newQuestionnaire.setExpirationDate(addQuestionnaireView.getExpirationDate());
        newQuestionnaire.setLabel(addQuestionnaireView.getLabel());
        newQuestionnaire.getTeacher().setId(addQuestionnaireView.getTeacherId());


        hibernateAdapter.save(newQuestionnaire);

        List<Term> allTerms = hibernateAdapter.getAll(Term.class);

        for (Term term : allTerms) {
            if (addQuestionnaireView.getAvailableTerms().contains(term.getId())) {
                QuestionnaireTerm qt = new QuestionnaireTerm(newQuestionnaire, term);
                hibernateAdapter.save(qt);
            }
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
