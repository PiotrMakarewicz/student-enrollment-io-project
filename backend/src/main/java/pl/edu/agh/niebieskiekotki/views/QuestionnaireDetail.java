package pl.edu.agh.niebieskiekotki.views;

import pl.edu.agh.niebieskiekotki.entitites.Questionnaire;

public class QuestionnaireDetail {
    Questionnaire questionnaire;
    Terms terms;

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public Terms getTerms() {
        return terms;
    }

    public QuestionnaireDetail(Questionnaire questionnaire){
        this.terms = new Terms();
        this.questionnaire = questionnaire;
    }
}
