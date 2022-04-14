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
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class QuestionnaireRouter {

    @PersistenceContext
    private EntityManager entityManager;

    List<Questionnaire> questionnaires = new ArrayList<>();

    QuestionnaireRouter(){
        Questionnaire q = new Questionnaire();
        q.setLabel("Example Questionnaire");
        q.setId(123L);
        questionnaires.add(q);
    }


    @GetMapping(value="/questionnaires")
    public List<Questionnaire> GetAll(){

        Session session = entityManager.unwrap(Session.class);
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Questionnaire> criteriaQuery = builder.createQuery(Questionnaire.class);

        Root<Questionnaire> root = criteriaQuery.from(Questionnaire.class);
        criteriaQuery.select(root);
        Query<Questionnaire> query = session.createQuery(criteriaQuery);
        List<Questionnaire> results = query.getResultList();
        System.out.println("Query results: " + results);
        return results;
    }

    @GetMapping(value="/questionnaires/{id}")
    public QuestionnaireDetail GetOne(@PathVariable Long id) throws NotFoundException {
        Questionnaire toReturn = questionnaires
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
        questionnaires.add(questionnaire);
        return questionnaires;
    }

    @PutMapping(value="/questionnaires/{id}")
    public Questionnaire Put(@PathVariable Long id,@RequestBody Questionnaire questionnaire) throws Exception{

        System.out.println("Enter put");

        Questionnaire toReturn = questionnaires
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

        Questionnaire toReturn = questionnaires
                .stream()
                .filter( q -> q.getId() != null && q.getId().equals( id))
                .findFirst()
                .orElse(null);

        if(toReturn == null)
            throw new NotFoundException("questionnaire with id=" + id);

        questionnaires.remove(toReturn);
        return toReturn;
    }
    
}
