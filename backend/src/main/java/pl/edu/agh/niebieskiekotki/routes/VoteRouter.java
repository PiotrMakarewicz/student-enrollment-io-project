package pl.edu.agh.niebieskiekotki.routes;

import org.springframework.web.bind.annotation.*;
import pl.edu.agh.niebieskiekotki.HibernateAdapter;
import pl.edu.agh.niebieskiekotki.entitites.*;
import pl.edu.agh.niebieskiekotki.errorsHandling.exceptions.NotFoundException;
import pl.edu.agh.niebieskiekotki.views.QuestionnaireResults;
import pl.edu.agh.niebieskiekotki.views.StudentVoteResults;
import pl.edu.agh.niebieskiekotki.views.VoteView;

import java.util.List;

@CrossOrigin
@RestController
public class VoteRouter {

    private final HibernateAdapter hibernateAdapter;

    public VoteRouter(HibernateAdapter hibernateAdapter) {
        this.hibernateAdapter = hibernateAdapter;
    }

    @PostMapping(value = "/vote/{hash}")
    public void AddVote(@RequestBody VoteView vote, @PathVariable String hash) throws NotFoundException {
        //get questionnaire and student connected with hash
        QuestionnaireAccess questionnaireAccess = hibernateAdapter.getOneWhereEq(QuestionnaireAccess.class, "linkPath", "vote/" + hash);
        if (questionnaireAccess == null) throw new NotFoundException("Invalid hash");
        Questionnaire questionnaire = questionnaireAccess.getQuestionnaire();
        Student student = questionnaireAccess.getStudent();

        //delete old votes
        hibernateAdapter.clearVotesWhere(questionnaire.getId(), student.getIndexNumber());

        //add new votes
        List<Term> terms = hibernateAdapter.getAll(Term.class);
        for (Term term : terms) {
            if (vote.getSelectedTerms().contains(term.getId())) {
                hibernateAdapter.save(new Vote(questionnaire, student, 1, term, ""));
            }
        }
    }

    @GetMapping(value = "/vote/{hash}")
    public StudentVoteResults getStudentVotes(@PathVariable String hash) throws NotFoundException {
        QuestionnaireAccess questionnaireAccess = hibernateAdapter.getOneWhereEq(QuestionnaireAccess.class, "linkPath", "vote/" + hash);
        if (questionnaireAccess == null) throw new NotFoundException("Invalid hash");
        Student student = questionnaireAccess.getStudent();
        Questionnaire questionnaire = questionnaireAccess.getQuestionnaire();

        return new StudentVoteResults(student, questionnaire);


    }

    @GetMapping(value = "/votes/{id}")
    public QuestionnaireResults getVotes(@PathVariable Long id) throws NotFoundException {

        Questionnaire questionnaire = hibernateAdapter.getById(Questionnaire.class, id);
        if (questionnaire == null)
            throw new NotFoundException("Not found questionnaire with id " + id);

        List<Vote> votes = questionnaire.getVotes();
        return new QuestionnaireResults(votes, questionnaire);
    }
}
