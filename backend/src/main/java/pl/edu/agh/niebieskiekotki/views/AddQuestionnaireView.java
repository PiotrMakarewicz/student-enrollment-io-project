package pl.edu.agh.niebieskiekotki.views;

import pl.edu.agh.niebieskiekotki.entitites.Student;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AddQuestionnaireView {
    private LocalDateTime expirationDate;
    private Long teacherId;

    private String label;
    private List<Long> availableTerms = new ArrayList<>();
    private List<Student> studentsInfo = new ArrayList<>();
    private boolean autoSendingLinks;

    public boolean isAutoSendingLinks() {
        return autoSendingLinks;
    }

    public void setAutoSendingLinks(boolean autoSendingLinks) {
        this.autoSendingLinks = autoSendingLinks;
    }

    public List<Student> getStudentsInfo() {
        return studentsInfo;
    }

    public void setStudentsInfo(List<Student> studentsInfo) {
        this.studentsInfo = studentsInfo;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "AddQuestionnaireView{" +
                "expirationDate=" + expirationDate +
                ", label='" + label + '\'' +
                ", availableTerms=" + availableTerms +
                '}';
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
