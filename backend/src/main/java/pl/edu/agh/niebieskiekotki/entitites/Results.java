package pl.edu.agh.niebieskiekotki.entitites;

import javax.persistence.*;

@Entity
public class Results {
    @ManyToOne
    @JoinColumn(name = "questionnaire_id")
    private Questionnaire questionnaire;

    @ManyToOne
    @JoinColumn(name = "term_id")
    private Term term;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Results(Questionnaire questionnaire, Term term, Student student) {
        this.questionnaire = questionnaire;
        this.term = term;
        this.student = student;
    }

    public Results() {
        this.questionnaire = new Questionnaire();
        this.term = new Term();
        this.student = new Student();
    }


    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
