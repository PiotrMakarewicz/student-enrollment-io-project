package pl.edu.agh.niebieskiekotki.views;

import pl.edu.agh.niebieskiekotki.entitites.*;

import java.util.ArrayList;
import java.util.List;

public class QuestionnaireResults {

    List<String> headers;
    List<QuestionnaireResultsRow> rows;
    List<Term> availableTerms;

    public QuestionnaireResults(List<Vote> votes, Questionnaire questionnaire) {

        availableTerms = new ArrayList<>();
        rows = new ArrayList<>();

        List<QuestionnaireTerm> questionnaireTerms = questionnaire.questionnaireTerms;

        for (QuestionnaireTerm term : questionnaireTerms)
            availableTerms.add(term.getTerm());

        availableTerms.sort(Term::compareTo);

        headers = new ArrayList<>();
        for (Term term : availableTerms) {
            headers.add(term.toString());
        }


        for (Vote vote : votes) {
            QuestionnaireResultsRow questionnaireResultsRow = null;

            for (QuestionnaireResultsRow row : rows)
                if (row.getStudent() == vote.getStudent())
                    questionnaireResultsRow = row;

            if (questionnaireResultsRow == null) {
                questionnaireResultsRow = new QuestionnaireResultsRow(vote.getStudent(), availableTerms);
                rows.add(questionnaireResultsRow);
            }
            questionnaireResultsRow.setTerm(vote.getTerm());

        }
    }

    public List<String> getHeaders() {
        return headers;
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    public List<QuestionnaireResultsRow> getRows() {
        return rows;
    }

    public void setRows(List<QuestionnaireResultsRow> rows) {
        this.rows = rows;
    }

    public List<Term> getAvailableTerms() {
        return availableTerms;
    }


}
