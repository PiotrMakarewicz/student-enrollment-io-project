package pl.edu.agh.niebieskiekotki.entitites;

import javax.persistence.*;

@Entity
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "questionnaire_id")
    private Questionnaire questionnaire;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    private int type; // type = 1 -> term available, type = 2 -> term impossible

    @ManyToOne
    @JoinColumn(name = "term_id")
    private Term term;
    private String note;

    public Vote(Questionnaire questionnaire, Student student, int type, Term term, String note) {
        this.questionnaire = questionnaire;
        this.student = student;
        this.type = type;
        this.term = term;
        this.note = note;
    }

    public Vote() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
