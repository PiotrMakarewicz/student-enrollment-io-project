package pl.edu.agh.niebieskiekotki.views;

import java.util.List;

public class VoteView {

    private Long questionnaire_id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private int indexNumber;
    private List<Long> selected_terms;


    public Long getQuestionnaire_id() {
        return questionnaire_id;
    }

    public void setQuestionnaire_id(Long questionnaire_id) {
        this.questionnaire_id = questionnaire_id;
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

    public List<Long> getSelected_terms() {
        return selected_terms;
    }

    public void setSelected_terms(List<Long> selected_terms) {
        this.selected_terms = selected_terms;
    }
}
