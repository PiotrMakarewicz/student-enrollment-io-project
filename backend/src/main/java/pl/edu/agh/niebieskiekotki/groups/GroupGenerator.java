package pl.edu.agh.niebieskiekotki.groups;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.niebieskiekotki.entitites.*;
import pl.edu.agh.niebieskiekotki.errorsHandling.exceptions.GroupCreationFailedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GroupGenerator {

    @Autowired
    private GenerationAlgorithm algorithm;

    public GenerationOutput generate(Questionnaire questionnaire, int numGroups) throws GroupCreationFailedException {
        Map<Student, List<Term>> studentsTerms = new HashMap<>();
        Map<Student, List<Term>> studentsImpossibleTerms = new HashMap<>();
        if (questionnaire.getQuestionnaireAccesses().size() == 0){
            throw new GroupCreationFailedException("There are no students assigned to this questionnaire, can't generate groups!");
        }
        for (QuestionnaireAccess acc : questionnaire.getQuestionnaireAccesses()){
            studentsTerms.put(acc.getStudent(), new ArrayList<>());
            studentsImpossibleTerms.put(acc.getStudent(), new ArrayList<>());
        }
        for (Vote vote: questionnaire.getVotes()){
            Student student = vote.getStudent();
            if (! studentsTerms.containsKey(student)){
                throw new GroupCreationFailedException("There are votes posted by unauthorized students in this questionnaire!");
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
