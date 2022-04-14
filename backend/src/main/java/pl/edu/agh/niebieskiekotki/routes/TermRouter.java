package pl.edu.agh.niebieskiekotki.routes;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.niebieskiekotki.DataBaseMock;
import pl.edu.agh.niebieskiekotki.views.CalendarView;

@CrossOrigin
@RestController
public class TermRouter {

    @GetMapping(value="/terms")
    public CalendarView getTerms(){
        return  new CalendarView(DataBaseMock.terms);
    }
}
