package pl.edu.agh.niebieskiekotki.entitites;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class QuestionnaireTerm {

    @ManyToOne
    @JoinColumn(name = "questionnaire_id")
    private Questionnaire questionnaire;

    @ManyToOne
    @JoinColumn(name = "term_id")
    private Term term;

    public QuestionnaireTerm(Questionnaire questionnaire, Term term) {
        this.questionnaire = questionnaire;
        this.term = term;
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
}
