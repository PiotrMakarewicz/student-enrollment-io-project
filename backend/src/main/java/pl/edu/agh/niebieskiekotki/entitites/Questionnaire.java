package pl.edu.agh.niebieskiekotki.entitites;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Entity
public class Questionnaire {

    @OneToMany(mappedBy = "questionnaire")
    @JsonIgnore
    public List<QuestionnaireAccess> questionnaireAccesses;

    public List<QuestionnaireTerm> getQuestionnaireTerms() {
        return questionnaireTerms;
    }

    public void setQuestionnaireTerms(List<QuestionnaireTerm> questionnaireTerms) {
        this.questionnaireTerms = questionnaireTerms;
    }

    @OneToMany(mappedBy = "questionnaire")
    @JsonIgnore
    public List<QuestionnaireTerm> questionnaireTerms;
    @OneToMany(mappedBy = "questionnaire")
    @JsonIgnore
    public List<Vote> votes;

    @OneToMany(mappedBy = "questionnaire")
    @JsonIgnore
    public List<Results> results;
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

    public List<QuestionnaireAccess> getQuestionnaireAccesses() {
        return questionnaireAccesses;
    }

    public void setQuestionnaireAccesses(List<QuestionnaireAccess> questionnaireAccesses) {
        this.questionnaireAccesses = questionnaireAccesses;
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

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

    public Map<Student,String> studentsWithLinks(){
        return questionnaireAccesses.stream().collect(Collectors.toMap(QuestionnaireAccess::getStudent,QuestionnaireAccess::getLinkPath));
    }

}
