package pl.edu.agh.niebieskiekotki.entitites;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Questionnaire {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    public Questionnaire(Long id,  LocalDateTime expirationDate, String label) {
        this.id = id;
        this.expirationDate = expirationDate;
        this.label = label;
    }


    public Questionnaire() {
    }

    @OneToMany(mappedBy = "questionnaire")
    private List<QuestionnaireAccess> questionnaireAccesses;

    private LocalDateTime expirationDate;

    private String label;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
