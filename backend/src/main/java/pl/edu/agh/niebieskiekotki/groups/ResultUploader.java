package pl.edu.agh.niebieskiekotki.groups;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.niebieskiekotki.HibernateAdapter;
import pl.edu.agh.niebieskiekotki.entitites.Questionnaire;
import pl.edu.agh.niebieskiekotki.entitites.Results;
import pl.edu.agh.niebieskiekotki.entitites.Student;
import pl.edu.agh.niebieskiekotki.entitites.Term;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class ResultUploader {

    @Autowired
    private HibernateAdapter hibernateAdapter;

    public void upload(Questionnaire questionnaire, GenerationOutput generationOutput){
        Map<Term, Set<Student>> termStudents = generationOutput.getTermStudents();
        for (Term term: termStudents.keySet()){
            for (Student student: termStudents.get(term)){
                Results r = new Results(questionnaire, term, student);
                hibernateAdapter.save(r);
            }
        }
    }
}
