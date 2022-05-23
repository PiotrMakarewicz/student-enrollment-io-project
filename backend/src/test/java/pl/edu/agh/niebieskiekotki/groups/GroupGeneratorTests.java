package pl.edu.agh.niebieskiekotki.groups;

import org.junit.jupiter.api.Test;
import pl.edu.agh.niebieskiekotki.entitites.Student;
import pl.edu.agh.niebieskiekotki.entitites.Term;
import pl.edu.agh.niebieskiekotki.utility.Days;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class GroupGeneratorTests {

    GenerationAlgorithm generator = new GenerationAlgorithm();

    @Test
    public void test(){
        Student s1 = new Student();
        s1.setId(0L);
        Student s2 = new Student();
        s2.setId(1L);
        Student s3 = new Student();
        s3.setId(2L);
        Student s4 = new Student();
        s4.setId(3L);
        Student s5 = new Student();
        s5.setId(4L);
        Student s6 = new Student();
        s6.setId(5L);
        Student s7 = new Student();
        s7.setId(6L);
        Student s8 = new Student();
        s8.setId(7L);

        Term t1 = new Term(1L, Days.Monday, 1, null);
        Term t2 = new Term(2L, Days.Tuesday, 2, null);
        Term t3 = new Term(3L, Days.Wednesday, 3, null);
        Term t4 = new Term(4L, Days.Thursday, 4, null);
        Term t5 = new Term(5L, Days.Friday, 5, null);
        Term t6 = new Term(6L, Days.Monday, 6, null);
        Term t7 = new Term(7L, Days.Thursday, 3, null);

        Map<Student, List<Term>> studentTerms = new HashMap<>();
        studentTerms.put(s1, List.of(t1, t2, t3));
        studentTerms.put(s2, List.of(t2, t3, t5));
        studentTerms.put(s3, List.of(t3));
        studentTerms.put(s4, List.of(t1, t5, t6));
        studentTerms.put(s5, List.of(t3, t4));
        studentTerms.put(s6, List.of(t3));
        studentTerms.put(s7, List.of(t1, t2, t3, t4, t5, t6));
        studentTerms.put(s8, List.of(t4, t5, t7));

        GenerationOutput output = generator.generate(studentTerms, 3);

        Set<Student> unassignedStudents = output.getUnassignedStudents();
        Map<Term, Set<Student>> termStudents = output.getTermStudents();

        assertEquals(3, output.getTermStudents().keySet().size());

        for (Student student: List.of(s1, s2, s3, s4, s5, s6, s7, s8)){
            if (unassignedStudents.contains(student)){
                assertTrue(termStudents.keySet().stream()
                                .noneMatch(t -> termStudents.get(t).contains(student)
                ));
            } else {
                assertTrue(termStudents.keySet().stream()
                                .filter(t -> termStudents.get(t).contains(student))
                                .count() == 1);
            }
        }

        for (Term term : termStudents.keySet()){
            for (Student student : termStudents.get(term)){
                assertTrue(studentTerms.get(student).contains(term));
            }
        }
    }
}
