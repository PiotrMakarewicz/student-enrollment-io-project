package pl.edu.agh.niebieskiekotki.views;

import java.time.LocalDateTime;
import java.util.List;

public class AddQuestionnaireView {
    private LocalDateTime expirationDate;
    private Long teacher_id;

    public Long getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(Long teacher_id) {
        this.teacher_id = teacher_id;
    }

    private String label;
    private List<Long> availableTerms;

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    @Override
    public String toString() {
        return "AddQuestionnaireView{" +
                "expirationDate=" + expirationDate +
                ", label='" + label + '\'' +
                ", availableTerms=" + availableTerms +
                '}';
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Long> getAvailableTerms() {
        return availableTerms;
    }

    public void setAvailableTerms(List<Long> availableTerms) {
        this.availableTerms = availableTerms;
    }

}
