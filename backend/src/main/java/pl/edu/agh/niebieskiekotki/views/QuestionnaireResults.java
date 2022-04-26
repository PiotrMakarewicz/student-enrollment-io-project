package pl.edu.agh.niebieskiekotki.views;

import pl.edu.agh.niebieskiekotki.DataBaseMock;
import pl.edu.agh.niebieskiekotki.HibernateAdapter;
import pl.edu.agh.niebieskiekotki.entitites.Questionnaire;
import pl.edu.agh.niebieskiekotki.entitites.QuestionnaireTerm;
import pl.edu.agh.niebieskiekotki.entitites.Term;
import pl.edu.agh.niebieskiekotki.entitites.Vote;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

public class QuestionnaireResults{

    List<String> headers;
    List<QuestionnaireResultsRow> rows;
    List<Term> questionnaireAvailableTerms;

    public QuestionnaireResults(List<Vote> votes, Questionnaire questionnaire){

         questionnaireAvailableTerms = new ArrayList<>();
         rows = new ArrayList<>();
        List<QuestionnaireTerm> questionnaireTerms = HibernateAdapter
                .getWhereEq(QuestionnaireTerm.class,"questionnaire", questionnaire);

        for(QuestionnaireTerm term : questionnaireTerms)
                questionnaireAvailableTerms.add(term.getTerm());

        headers = new ArrayList<>();
        for(Term term : questionnaireAvailableTerms){
            headers.add(term.toString());
        }


        for(Vote vote : votes){
            QuestionnaireResultsRow  questionnaireResultsRow = null;

            for(QuestionnaireResultsRow row : rows)
                if(row.student == vote.getStudent().getIndexNumber())
                    questionnaireResultsRow = row;

        if(questionnaireResultsRow == null) {
            questionnaireResultsRow = new QuestionnaireResultsRow(vote.getStudent().getIndexNumber());
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

    class QuestionnaireResultsRow{
        int student;
        int[] studentChoose;

        public QuestionnaireResultsRow(int student) {
            this.student = student;
            studentChoose = new int[questionnaireAvailableTerms.size()];
        }

        void setTerm(Term term){
            System.out.println(questionnaireAvailableTerms);
            System.out.println(term);
            Integer index = questionnaireAvailableTerms.indexOf(term);
            System.out.println(index);
            if(index == -1) return;
            studentChoose[index] = 1;
        }

        public int getStudent() {
            return student;
        }

        public void setStudent(int student) {
            this.student = student;
        }

        public int[] getStudentChoose() {
            return studentChoose;
        }

        public void setStudentChoose(int[] studentChoose) {
            this.studentChoose = studentChoose;
        }
    }
}
