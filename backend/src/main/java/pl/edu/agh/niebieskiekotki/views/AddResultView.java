package pl.edu.agh.niebieskiekotki.views;

public class AddResultView {
    private Long studentId;
    private Long questionnaireId;
    private Long termId;

    public AddResultView(Long studentId, Long questionnaireId, Long termId) {
        this.studentId = studentId;
        this.questionnaireId = questionnaireId;
        this.termId = termId;
    }


    @Override
    public String toString() {
        return "AddResultView{" +
                "studentId=" + studentId +
                ", questionnaireId=" + questionnaireId +
                ", termId=" + termId +
                '}';
    }


    public Long getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Long questionnaire_id) {
        this.questionnaireId = questionnaire_id;
    }

    public Long getTermId() {
        return termId;
    }

    public void setTermId(Long termId) {
        this.termId = termId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getStudentId() {
        return studentId;
    }
}
