package pl.edu.agh.niebieskiekotki.routes;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.niebieskiekotki.HibernateAdapter;
import pl.edu.agh.niebieskiekotki.entitites.Vote;
import pl.edu.agh.niebieskiekotki.errorsHandling.exceptions.NotFoundException;
import pl.edu.agh.niebieskiekotki.views.ImposibilitiesView;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class ImposibilityRouter {
    private final HibernateAdapter hibernateAdapter;

    public ImposibilityRouter(HibernateAdapter hibernateAdapter) {
        this.hibernateAdapter = hibernateAdapter;
    }

    @GetMapping(value = "/imposibilities/{id}")
    public ImposibilitiesView getImposibilities(@PathVariable Long id) throws NotFoundException{
        List<Vote> votes = hibernateAdapter.getWhereEq(Vote.class, "questionnaire", id);
        if (votes==null) {
            throw new NotFoundException("Not found questionnaire with id:" + id);
        }
        List<Vote> votesWithImposibilities = new ArrayList<>();
        for (Vote vote : votes){
            if (vote.getType()==2){
                votesWithImposibilities.add(vote);
            }
        }
        return new ImposibilitiesView(votesWithImposibilities);
    }
}
