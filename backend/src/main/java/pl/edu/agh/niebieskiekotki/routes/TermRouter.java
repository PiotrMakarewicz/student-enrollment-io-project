package pl.edu.agh.niebieskiekotki.routes;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.niebieskiekotki.HibernateAdapter;
import pl.edu.agh.niebieskiekotki.entitites.Term;
import pl.edu.agh.niebieskiekotki.entitites.Timeslot;
import pl.edu.agh.niebieskiekotki.views.CalendarView;

import javax.transaction.Transactional;


@CrossOrigin
@RestController
public class TermRouter {

    private final HibernateAdapter hibernateAdapter;

    public TermRouter(HibernateAdapter hibernateAdapter) {
        this.hibernateAdapter = hibernateAdapter;
    }
    @Transactional
    @GetMapping(value = "/terms")
    public CalendarView getTerms() {

         return new CalendarView(
                hibernateAdapter.getAll(Term.class),
                hibernateAdapter.getAll(Timeslot.class)
        );
    }
}
