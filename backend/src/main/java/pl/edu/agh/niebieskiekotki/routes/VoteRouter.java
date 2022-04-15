package pl.edu.agh.niebieskiekotki.routes;

import org.springframework.web.bind.annotation.*;
import pl.edu.agh.niebieskiekotki.DataBaseMock;
import pl.edu.agh.niebieskiekotki.entitites.Questionnaire;
import pl.edu.agh.niebieskiekotki.entitites.Student;
import pl.edu.agh.niebieskiekotki.entitites.Term;
import pl.edu.agh.niebieskiekotki.entitites.Vote;
import pl.edu.agh.niebieskiekotki.errorsHandling.exceptions.NotFoundException;
import pl.edu.agh.niebieskiekotki.views.CalendarView;
import pl.edu.agh.niebieskiekotki.views.QuestionnaireResults;
import pl.edu.agh.niebieskiekotki.views.VoteView;

import javax.xml.crypto.Data;

@CrossOrigin
@RestController
public class VoteRouter {
    @PostMapping(value="/vote")
    public void AddVote(@RequestBody VoteView vote){
        Student student = new Student(vote.getFirstName(), vote.getLastName(), vote.getEmailAddress(), vote.getIndexNumber());
        DataBaseMock.students.add( student );

        Questionnaire questionnaire = null;
        for(Questionnaire q : DataBaseMock.questionnaires){
            if(q.getId().equals(vote.getQuestionnaire_id())){
                questionnaire = q;
                break;
            }
        }

        System.out.println("questionnaire:" + questionnaire);

        if(questionnaire != null){
            for( Term term : DataBaseMock.terms ){
                System.out.println(vote.getSelected_terms() + ":" +term);
                if(vote.getSelected_terms().contains(term.getId())){
                    DataBaseMock.votes.add(new Vote(questionnaire, student,  1 ,term,""));
                }
            }
        }

        System.out.println(DataBaseMock.votes);
    }

    @GetMapping(value="/vote/{id}")
    public QuestionnaireResults getVotes(@PathVariable Long id) throws NotFoundException {
        Questionnaire questionnaire = null;
        for(Questionnaire q : DataBaseMock.questionnaires){
            if(q.getId().equals(id)){
                questionnaire = q;
                break;
            }
        }

        if(questionnaire == null) throw new NotFoundException("Not found specific Questionnaire");
        return new QuestionnaireResults(DataBaseMock.votes, questionnaire);
    }
}
