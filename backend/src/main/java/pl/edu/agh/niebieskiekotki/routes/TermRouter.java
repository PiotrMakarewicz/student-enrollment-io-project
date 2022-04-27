package pl.edu.agh.niebieskiekotki.routes;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.niebieskiekotki.DataBaseMock;
import pl.edu.agh.niebieskiekotki.HibernateAdapter;
import pl.edu.agh.niebieskiekotki.entitites.Questionnaire;
import pl.edu.agh.niebieskiekotki.entitites.Term;
import pl.edu.agh.niebieskiekotki.views.CalendarView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@CrossOrigin
@RestController
public class TermRouter {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private HibernateAdapter hibernateAdapter;
    @GetMapping(value="/terms")
    public CalendarView getTerms(){
        return  new CalendarView(hibernateAdapter.getAll(Term.class));
    }
}
