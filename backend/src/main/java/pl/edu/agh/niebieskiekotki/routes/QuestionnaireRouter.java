package pl.edu.agh.niebieskiekotki.routes;

import org.springframework.web.bind.annotation.*;
import pl.edu.agh.niebieskiekotki.DataBaseMock;
import pl.edu.agh.niebieskiekotki.entitites.Questionnaire;
import pl.edu.agh.niebieskiekotki.entitites.QuestionnaireTerm;
import pl.edu.agh.niebieskiekotki.entitites.Term;
import pl.edu.agh.niebieskiekotki.errorsHandling.exceptions.NotFoundException;
import pl.edu.agh.niebieskiekotki.views.AddQuestionnaireView;
import pl.edu.agh.niebieskiekotki.views.QuestionnaireDetail;


import java.util.List;

@CrossOrigin
@RestController
public class QuestionnaireRouter {

    @GetMapping(value="/questionnaires")
    public List<Questionnaire> GetAll(){
        return  DataBaseMock.questionnaires;
    }

    @GetMapping(value="/questionnaires/{id}")
    public QuestionnaireDetail GetOne(@PathVariable Long id) throws NotFoundException {
        Questionnaire toReturn =  DataBaseMock.questionnaires
                .stream()
                .filter( q -> q.getId() != null && q.getId().equals(id))
                .findFirst()
                .orElse(null);
        System.out.println(id);
        System.out.println(toReturn);
        if(toReturn == null)
            throw new NotFoundException("Not found questionnare with id:" + id );

        return new QuestionnaireDetail(toReturn);
    }

    @PostMapping(value="/questionnaires")
    public List<Questionnaire> Post(@RequestBody AddQuestionnaireView addQuestionnaireView){
        System.out.println(addQuestionnaireView);
        Questionnaire questionnaire = new Questionnaire(addQuestionnaireView.getExpirationDate(),addQuestionnaireView.getLabel());

        if(addQuestionnaireView.getAvailableTerms() != null)
        for( Term term : DataBaseMock.terms )
            if(addQuestionnaireView.getAvailableTerms().contains(term.getId())){
                DataBaseMock.questionnaireTerms.add(new QuestionnaireTerm(questionnaire,term));
            }

        DataBaseMock.questionnaires.add(questionnaire);
        return  DataBaseMock.questionnaires;
    }

    @PutMapping(value="/questionnaires/{id}")
    public Questionnaire Put(@PathVariable Long id,@RequestBody Questionnaire questionnaire) throws Exception{

        Questionnaire toReturn =  DataBaseMock.questionnaires
                .stream()
                .filter( q -> q.getId() != null && q.getId().equals(id))
                .findFirst()
                .orElse(null);
        System.out.println(toReturn);

        if(toReturn == null)
            throw new NotFoundException("Not found questionnare with id:" + id );

        toReturn.setLabel( questionnaire.getLabel());

        return toReturn;
    }

    @DeleteMapping(value="/questionnaires/{id}")
    public Questionnaire Delete(@PathVariable Long id) throws Exception{

        Questionnaire toReturn =  DataBaseMock.questionnaires
                .stream()
                .filter( q -> q.getId() != null && q.getId().equals( id))
                .findFirst()
                .orElse(null);

        if(toReturn == null)
            throw new NotFoundException("questionnaire with id=" + id);

        DataBaseMock.questionnaires.remove(toReturn);
        return toReturn;
    }
    
}
