package pl.edu.agh.niebieskiekotki.entitites;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Questionnaire {

    @OneToMany(mappedBy = "questionnaire")
    @JsonIgnore
    public List<QuestionnaireAccess> questionnaireAccesses;
    @OneToMany(mappedBy = "questionnaire")
    @JsonIgnore
    public List<QuestionnaireTerm> questionnaireTerms;
    @OneToMany(mappedBy = "questionnaire")
    @JsonIgnore
    public List<Vote> votes;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
    private LocalDateTime expirationDate;

    private String label;

    public Questionnaire(LocalDateTime expirationDate, String label) {
        this.expirationDate = expirationDate;
        this.label = label;
    }

    public Questionnaire() {
        teacher = new Teacher();
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setQuestionnaireTerms(List<QuestionnaireTerm> questionnaireTerms) {
        this.questionnaireTerms = questionnaireTerms;
    }

    public Map<Student,String> getLinks(){
        Map<Student,String> links=new HashMap<>();
        for (QuestionnaireAccess questionnaireAccess : questionnaireAccesses){
            links.put(questionnaireAccess.getStudent(),"localhost:3000/vote/"+questionnaireAccess.getLinkPath());
        }
        return links;
    }
}
