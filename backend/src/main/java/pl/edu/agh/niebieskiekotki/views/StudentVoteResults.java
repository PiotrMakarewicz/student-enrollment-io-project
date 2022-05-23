package pl.edu.agh.niebieskiekotki.views;

import pl.edu.agh.niebieskiekotki.entitites.*;

import java.util.ArrayList;
import java.util.List;

public class StudentVoteResults {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private int indexNumber;
    private List<Long> availableTerms;
    private List<Long> selectedTerms;

    public StudentVoteResults(Student student, Questionnaire questionnaire){
        this.firstName=student.getFirstName();
        this.lastName=student.getLastName();
        this.emailAddress=student.getEmailAddress();
        this.indexNumber= student.getIndexNumber();
        this.selectedTerms = new ArrayList<>();
        this.availableTerms = new ArrayList<>();
        for(Vote vote : questionnaire.getVotes()){
            selectedTerms.add(vote.getTerm().getId());
        }
        for(QuestionnaireTerm term : questionnaire.getQuestionnaireTerms()){
            availableTerms.add(term.getTerm().getId());
        }
    }
}
