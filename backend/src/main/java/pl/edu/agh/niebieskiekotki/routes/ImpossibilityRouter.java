package pl.edu.agh.niebieskiekotki.routes;

import org.springframework.web.bind.annotation.*;
import pl.edu.agh.niebieskiekotki.HibernateAdapter;
import pl.edu.agh.niebieskiekotki.entitites.Vote;
import pl.edu.agh.niebieskiekotki.errorsHandling.exceptions.NotFoundException;
import pl.edu.agh.niebieskiekotki.views.DeletedImpossibilitiesView;
import pl.edu.agh.niebieskiekotki.views.ImpossibilitiesView;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class ImpossibilityRouter {
    private final HibernateAdapter hibernateAdapter;

    public ImpossibilityRouter(HibernateAdapter hibernateAdapter) {
        this.hibernateAdapter = hibernateAdapter;
    }

    @GetMapping(value = "/impossibilities/{id}")
    public ImpossibilitiesView getImpossibilities(@PathVariable Long id) throws NotFoundException {
        List<Vote> votes = hibernateAdapter.getWhereEq(Vote.class, "questionnaire", id);
        if (votes == null) {
            throw new NotFoundException("Not found questionnaire with id:" + id);
        }
        List<Vote> votesWithImposibilities = new ArrayList<>();
        for (Vote vote : votes) {
            if (vote.getType() == 2) {
                votesWithImposibilities.add(vote);
            }
        }
        return new ImpossibilitiesView(votesWithImposibilities);
    }

    @PostMapping(value = "/impossibilities/{id}")
    public void deleteImpossibilities(@PathVariable Long id, @RequestBody DeletedImpossibilitiesView deleted) throws NotFoundException {

        List<Vote> votes = hibernateAdapter.getWhereEq(Vote.class, "questionnaire", id);
        if (votes == null) {
            throw new NotFoundException("Not found questionnaire with id:" + id);
        }
        for (Vote vote : votes) {
            if (vote.getType() == 2) {
                if (deleted.getDeletedImpossibilities().contains(vote.getId())) {
                    hibernateAdapter.clearVoteById(vote.getId());
                }
            }
        }
    }

}
