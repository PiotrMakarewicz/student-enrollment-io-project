package pl.edu.agh.niebieskiekotki.entitites;

import javax.persistence.*;

@Entity
public class QuestionnaireTerm {

    @ManyToOne
    @JoinColumn(name = "questionnaire_id")
    private Questionnaire questionnaire;

    @ManyToOne
    @JoinColumn(name = "term_id")
    private Term term;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public QuestionnaireTerm(Questionnaire questionnaire, Term term) {
        this.questionnaire = questionnaire;
        this.term = term;
    }

    public QuestionnaireTerm() {
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
