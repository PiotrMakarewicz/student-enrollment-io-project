package pl.edu.agh.niebieskiekotki.routes;

import org.springframework.web.bind.annotation.*;
import pl.edu.agh.niebieskiekotki.HibernateAdapter;
import pl.edu.agh.niebieskiekotki.entitites.Questionnaire;
import pl.edu.agh.niebieskiekotki.entitites.Student;
import pl.edu.agh.niebieskiekotki.entitites.Term;
import pl.edu.agh.niebieskiekotki.entitites.Vote;
import pl.edu.agh.niebieskiekotki.errorsHandling.exceptions.NotFoundException;
import pl.edu.agh.niebieskiekotki.views.QuestionnaireResults;
import pl.edu.agh.niebieskiekotki.views.VoteView;

import java.util.List;

@CrossOrigin
@RestController
public class VoteRouter {

    private final HibernateAdapter hibernateAdapter;

    public VoteRouter(HibernateAdapter hibernateAdapter) {
        this.hibernateAdapter = hibernateAdapter;
    }

    @PostMapping(value = "/vote")
    public void AddVote(@RequestBody VoteView vote) throws NotFoundException {

        System.out.println("got a post request on /vote");

        Student student = new Student(vote.getFirstName(), vote.getLastName(), vote.getEmailAddress(), vote.getIndexNumber());
        hibernateAdapter.save(student);


        Questionnaire questionnaire = hibernateAdapter.getById(Questionnaire.class, vote.getQuestionnaireId());
        if (questionnaire == null) {
            System.out.println("soemthign wetn very wrong");
            throw new NotFoundException("Not found questionnaire with id " + vote.getQuestionnaireId());
        }

        System.out.println("questionnaire:" + questionnaire);

        List<Term> terms = hibernateAdapter.getAll(Term.class);

        System.out.println(terms);

        for (Term term : terms) {
            if (vote.getSelectedTerms().contains(term.getId())) {
                hibernateAdapter.save(new Vote(questionnaire, student, 1, term, ""));
            }
        }
    }

    @GetMapping(value = "/vote/{id}")
    public QuestionnaireResults getVotes(@PathVariable Long id) throws NotFoundException {

        Questionnaire questionnaire = hibernateAdapter.getById(Questionnaire.class, id);
        if (questionnaire == null)
            throw new NotFoundException("Not found questionnaire with id " + id);


        List<Vote> votes = questionnaire.votes;

        return new QuestionnaireResults(votes, questionnaire);
    }
}
