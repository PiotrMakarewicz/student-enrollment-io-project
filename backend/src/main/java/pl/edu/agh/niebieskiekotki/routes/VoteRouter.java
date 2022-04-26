package pl.edu.agh.niebieskiekotki.routes;

import org.springframework.web.bind.annotation.*;
import pl.edu.agh.niebieskiekotki.DataBaseMock;
import pl.edu.agh.niebieskiekotki.HibernateAdapter;
import pl.edu.agh.niebieskiekotki.entitites.Questionnaire;
import pl.edu.agh.niebieskiekotki.entitites.Student;
import pl.edu.agh.niebieskiekotki.entitites.Term;
import pl.edu.agh.niebieskiekotki.entitites.Vote;
import pl.edu.agh.niebieskiekotki.errorsHandling.exceptions.NotFoundException;
import pl.edu.agh.niebieskiekotki.views.CalendarView;
import pl.edu.agh.niebieskiekotki.views.QuestionnaireResults;
import pl.edu.agh.niebieskiekotki.views.VoteView;

import javax.xml.crypto.Data;
import java.util.List;

@CrossOrigin
@RestController
public class VoteRouter {
    @PostMapping(value="/vote")
    public void AddVote(@RequestBody VoteView vote) throws NotFoundException {

        Student student = new Student(vote.getFirstName(), vote.getLastName(), vote.getEmailAddress(), vote.getIndexNumber());
        HibernateAdapter.save(student);


        Questionnaire questionnaire = HibernateAdapter.getById(Questionnaire.class, vote.getQuestionnaire_id());
        if(questionnaire == null)
            throw new NotFoundException("Not found questionnaire with id " + vote.getQuestionnaire_id());

        System.out.println("questionnaire:" + questionnaire);

       List<Term> terms = HibernateAdapter.getAll(Term.class);

        for( Term term : terms ){
            if(vote.getSelected_terms().contains(term.getId())){
                HibernateAdapter.save(new Vote(questionnaire, student,  1 ,term,""));
            }
        }

        System.out.println(DataBaseMock.votes);
        return;
    }

    @GetMapping(value="/vote/{id}")
    public QuestionnaireResults getVotes(@PathVariable Long id) throws NotFoundException {

        Questionnaire questionnaire = HibernateAdapter.getById(Questionnaire.class, id);
        if(questionnaire == null)
            throw new NotFoundException("Not found questionnaire with id " + id);


        List<Vote> votes = questionnaire.votes;

        return new QuestionnaireResults(votes, questionnaire);
    }
}
