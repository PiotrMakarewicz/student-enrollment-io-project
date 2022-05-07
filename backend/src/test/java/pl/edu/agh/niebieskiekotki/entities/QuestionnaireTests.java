package pl.edu.agh.niebieskiekotki.entities;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pl.edu.agh.niebieskiekotki.entitites.Questionnaire;
import pl.edu.agh.niebieskiekotki.entitites.QuestionnaireAccess;
import pl.edu.agh.niebieskiekotki.entitites.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class QuestionnaireTests {
    @Test
    void generateStudentsWithLinksTest(){
        Student[] students = new Student[]{new Student("a","b","a@com.pl",111111),
                                            new Student("c","d","b@com.pl",222222),
                                            new Student("e","f","c@com.pl",333333)};

        String[] links = new String[]{"link1","link2","link3"};
        ArrayList<QuestionnaireAccess> list = new ArrayList<>();
        Map<Student,String> fixedMap = new HashMap<>();
        for (int i = 0; i < students.length; i++) {
            QuestionnaireAccess questionnaireAccess = new QuestionnaireAccess();
            questionnaireAccess.setStudent(students[i]);
            questionnaireAccess.setLinkPath(links[i]);
            list.add(questionnaireAccess);
            fixedMap.put(students[i],links[i]);
        }
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setQuestionnaireAccesses(list);
        assertThat(fixedMap).isEqualTo(questionnaire.studentsWithLinks());

    }

}
