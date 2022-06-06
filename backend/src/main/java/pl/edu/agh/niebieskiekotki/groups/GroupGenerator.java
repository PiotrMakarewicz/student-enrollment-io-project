package pl.edu.agh.niebieskiekotki.groups;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.niebieskiekotki.HibernateAdapter;
import pl.edu.agh.niebieskiekotki.entitites.Questionnaire;
import pl.edu.agh.niebieskiekotki.entitites.Student;
import pl.edu.agh.niebieskiekotki.entitites.Term;
import pl.edu.agh.niebieskiekotki.entitites.Vote;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GroupGenerator {

    @Autowired
    private GenerationAlgorithm algorithm;

    public GenerationOutput generate(Questionnaire questionnaire, int numGroups){
        Map<Student, List<Term>> studentsTerms = new HashMap<>();
        Map<Student, List<Term>> studentsImpossibleTerms = new HashMap<>();
        for (Vote vote: questionnaire.getVotes()){
            Student student = vote.getStudent();
            if (! studentsTerms.containsKey(student)){
                studentsTerms.put(student, new ArrayList<>());
                studentsImpossibleTerms.put(student, new ArrayList<>());
            }

            int type = vote.getType();
            if (type == 1){
                Term term = vote.getTerm();
                studentsTerms.get(student).add(term);
            } else if (type == 2) {
                Term term = vote.getTerm();
                studentsImpossibleTerms.get(student).add(term);
            }
        }
        return algorithm.generate(studentsTerms, studentsImpossibleTerms, numGroups, 2);
    }

}
