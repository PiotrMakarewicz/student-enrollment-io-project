package pl.edu.agh.niebieskiekotki.entitites;

import javax.persistence.*;

@Entity
public class QuestionnaireAccess {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "questionnaire_id")
    private Questionnaire questionnaire;

    private String linkPath;

    private String password;

    public QuestionnaireAccess(Student student, Questionnaire questionnaire) {
        this.student = student;
        this.questionnaire = questionnaire;
        linkPath= "" + questionnaire.getId();
    }

    public QuestionnaireAccess(Student student, Questionnaire questionnaire, String hash) {
        this.student = student;
        this.questionnaire = questionnaire;
        this.linkPath = "vote/" + hash;
    }

    public QuestionnaireAccess() {
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public Long getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public String getLinkPath() {
        return linkPath;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setLinkPath(String linkPath) {
        this.linkPath = linkPath;
    }

    @Override
    public String toString() {
        return "QuestionnaireAccess{" +
                "id=" + id +
                ", linkPath='" + linkPath + '\'' +
                '}';
    }
}