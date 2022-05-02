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

    public QuestionnaireAccess() {
    }
}
