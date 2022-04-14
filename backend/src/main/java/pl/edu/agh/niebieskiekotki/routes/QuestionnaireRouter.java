package pl.edu.agh.niebieskiekotki.routes;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.niebieskiekotki.DataBaseMock;
import pl.edu.agh.niebieskiekotki.entitites.Questionnaire;
import pl.edu.agh.niebieskiekotki.errorsHandling.exceptions.NotFoundException;
import pl.edu.agh.niebieskiekotki.views.QuestionnaireDetail;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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
    public List<Questionnaire> Post(@RequestBody Questionnaire questionnaire){
        DataBaseMock.questionnaires.add(questionnaire);
        return  DataBaseMock.questionnaires;
    }

    @PutMapping(value="/questionnaires/{id}")
    public Questionnaire Put(@PathVariable Long id,@RequestBody Questionnaire questionnaire) throws Exception{

        System.out.println("Enter put");

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
