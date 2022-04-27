package pl.edu.agh.niebieskiekotki.entitites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Questionnaire {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    public static Long lastId = 1l;

    @OneToMany(mappedBy = "questionnaire")
    @JsonIgnore
    public List<QuestionnaireAccess> questionnaireAccesses;

    @OneToMany(mappedBy = "questionnaire")
    @JsonIgnore
    public List<QuestionnaireTerm> questionnaireTerms;

    @OneToMany(mappedBy = "questionnaire")
    @JsonIgnore
    public List<Vote> votes;

    private LocalDateTime expirationDate;

    private String label;

    public Questionnaire(  LocalDateTime expirationDate, String label) {
        this.expirationDate = expirationDate;
        this.label = label;
    }

    @Override
    public String toString() {
        return "Questionnaire{" +
                "id=" + id +
                ", teacher=" + teacher +
                ", questionnaireAccesses=" + questionnaireAccesses +
                ", expirationDate=" + expirationDate +
                ", label='" + label + '\'' +
                '}';
    }

    public Questionnaire() {
        teacher = new Teacher();
    }

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
