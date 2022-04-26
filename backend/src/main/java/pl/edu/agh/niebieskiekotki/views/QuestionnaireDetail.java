package pl.edu.agh.niebieskiekotki.views;

import pl.edu.agh.niebieskiekotki.DataBaseMock;
import pl.edu.agh.niebieskiekotki.HibernateAdapter;
import pl.edu.agh.niebieskiekotki.entitites.Questionnaire;
import pl.edu.agh.niebieskiekotki.entitites.QuestionnaireTerm;
import pl.edu.agh.niebieskiekotki.entitites.Term;

import java.util.ArrayList;
import java.util.List;

public class QuestionnaireDetail {
    Questionnaire detail;
    List<Long> terms;


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

    public QuestionnaireDetail(Questionnaire questionnaire ){
        this.detail = questionnaire;
        this.terms = new ArrayList<>();
        List<QuestionnaireTerm> questionnaireTerms = HibernateAdapter.getAll(QuestionnaireTerm.class);

        for (QuestionnaireTerm term : questionnaireTerms) {
            if(term.getQuestionnaire().getId().equals(questionnaire.getId()))
                terms.add(term.getTerm().getId());
        }
    }
}
