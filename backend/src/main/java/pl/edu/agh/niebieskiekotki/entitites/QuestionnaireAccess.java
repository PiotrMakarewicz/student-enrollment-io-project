package pl.edu.agh.niebieskiekotki.entitites;

import javax.persistence.*;

@Entity
public class QuestionnaireAccess {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "questionnaire_id")
    private Questionnaire questionnaire;

    private String linkPath;

    private String password;

}
