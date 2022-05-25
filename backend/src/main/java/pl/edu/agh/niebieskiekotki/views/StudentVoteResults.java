package pl.edu.agh.niebieskiekotki.views;

import pl.edu.agh.niebieskiekotki.entitites.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentVoteResults {
    private Student student;
    private List<Long> availableTerms;
    private List<Long> selectedTerms;
    private Map<Long, String> impossibleTerms;

    public StudentVoteResults(Student student, Questionnaire questionnaire) {
        this.student=student;
        this.selectedTerms = new ArrayList<>();
        this.availableTerms = new ArrayList<>();
        this.impossibleTerms = new HashMap<>();
        for (Vote vote : questionnaire.getVotes()) {
            if (vote.getType() == 1) {
                selectedTerms.add(vote.getTerm().getId());
            } else if (vote.getType() == 2) {
                impossibleTerms.put(vote.getTerm().getId(), vote.getNote());
            }
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

    public Map<Long, String> getImpossibleTerms() {
        return impossibleTerms;
    }
}
