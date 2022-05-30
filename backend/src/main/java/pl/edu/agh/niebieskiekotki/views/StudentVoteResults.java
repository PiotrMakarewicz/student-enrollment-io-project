package pl.edu.agh.niebieskiekotki.views;

import pl.edu.agh.niebieskiekotki.entitites.*;

import java.util.ArrayList;
import java.util.List;

public class StudentVoteResults {
    private Student student;
    private String label;
    private List<Long> availableTerms;
    private List<Long> selectedTerms;

    public StudentVoteResults(Student student, Questionnaire questionnaire) {
        this.student = student;
        this.label = questionnaire.getLabel();
        this.selectedTerms = new ArrayList<>();
        this.availableTerms = new ArrayList<>();
        for (Vote vote : questionnaire.getVotes()) {
            selectedTerms.add(vote.getTerm().getId());
        }
        for (QuestionnaireTerm term : questionnaire.getQuestionnaireTerms()) {
            availableTerms.add(term.getTerm().getId());
        }
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Long> getAvailableTerms() {
        return availableTerms;
    }

    public void setAvailableTerms(List<Long> availableTerms) {
        this.availableTerms = availableTerms;
    }

    public List<Long> getSelectedTerms() {
        return selectedTerms;
    }

    public void setSelectedTerms(List<Long> selectedTerms) {
        this.selectedTerms = selectedTerms;
    }

    public String getLabel() { return label; }
}
