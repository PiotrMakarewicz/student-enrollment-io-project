package pl.edu.agh.niebieskiekotki.views;

import pl.edu.agh.niebieskiekotki.entitites.Student;
import pl.edu.agh.niebieskiekotki.entitites.Term;

import java.util.ArrayList;
import java.util.List;

public class QuestionnaireResultsRow{
    private Student student;
    private int[] studentChoices;
    List<Term> availableTerms;


    public QuestionnaireResultsRow(Student student,  List<Term> availableTerms) {
        this.student = student;
        studentChoices = new int[availableTerms.size()];
        this.availableTerms = availableTerms;
    }

    void setTerm(Term term) {
        Integer index = availableTerms.indexOf(term);
        if (index == -1) return;
        studentChoices[index] = 1;
    }

    public int getStudentIndex() {
        return student.getIndexNumber();
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int[] getStudentChoose() {
        return studentChoices;
    }

    public void setStudentChoose(int[] studentChoose) {
        this.studentChoices = studentChoose;
    }

    public List<Term> getTermList(){
        ArrayList<Term> result = new ArrayList<>();
        for (int i = 0; i < studentChoices.length; i++) {
            if (studentChoices[i] == 1){
                result.add(availableTerms.get(i));
            }
        }
        return result;
    }
}