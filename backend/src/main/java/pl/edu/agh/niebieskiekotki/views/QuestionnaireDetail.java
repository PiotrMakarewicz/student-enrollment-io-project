package pl.edu.agh.niebieskiekotki.views;

import pl.edu.agh.niebieskiekotki.entitites.Questionnaire;
import pl.edu.agh.niebieskiekotki.entitites.QuestionnaireTerm;

import java.util.ArrayList;
import java.util.List;

public class QuestionnaireDetail {
    Questionnaire detail;
    List<Long> terms;


    public QuestionnaireDetail(Questionnaire questionnaire) {
        this.detail = questionnaire;
        this.terms = new ArrayList<>();
        List<QuestionnaireTerm> questionnaireTerms = questionnaire.questionnaireTerms;

        for (QuestionnaireTerm term : questionnaireTerms) {
            if (term.getQuestionnaire().getId().equals(questionnaire.getId()))
                terms.add(term.getTerm().getId());
        }
    }

    public Questionnaire getDetail() {
        return detail;
    }

    public void setDetail(Questionnaire detail) {
        this.detail = detail;
    }

    public List<Long> getTerms() {
        return terms;
    }

    public void setTerms(List<Long> terms) {
        this.terms = terms;
    }
}
