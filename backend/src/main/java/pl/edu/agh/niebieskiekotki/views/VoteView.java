package pl.edu.agh.niebieskiekotki.views;

import java.util.List;
import java.util.Map;

public class VoteView {

    private Long questionnaireId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private int indexNumber;
    private List<Long> selectedTerms;
    private Map<Long, String> impossibleTerms;


    public Long getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Long questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(int indexNumber) {
        this.indexNumber = indexNumber;
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

    public void setImpossibleTerms(Map<Long, String> impossibleTerms) {
        this.impossibleTerms = impossibleTerms;
    }
}
