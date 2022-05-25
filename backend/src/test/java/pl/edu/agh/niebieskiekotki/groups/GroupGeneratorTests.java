package pl.edu.agh.niebieskiekotki.groups;

import org.junit.jupiter.api.Test;
import pl.edu.agh.niebieskiekotki.entitites.Student;
import pl.edu.agh.niebieskiekotki.entitites.Term;
import pl.edu.agh.niebieskiekotki.utility.Days;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class GroupGeneratorTests {

    GenerationAlgorithm generator = new GenerationAlgorithm();

    @Test
    public void testSimple(){
        Student s1 = new Student(0L);
        Student s2 = new Student(1L);
        Student s3 = new Student(2L);
        Student s4 = new Student(3L);
        Student s5 = new Student(4L);
        Student s6 = new Student(5L);
        Student s7 = new Student(6L);
        Student s8 = new Student(7L);

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

        GenerationOutput output = generator.generate(studentTerms, 3, 3);

        testOutput(output, List.of(s1, s2, s3, s4, s5, s6, s7, s8), studentTerms, 3);
    }

    @Test
    public void testLargeRandom(){
        List<Student> students = new ArrayList<>();
        List<Term> terms = new ArrayList<>();
        Map<Student, List<Term>> studentTerms = new HashMap<>();

        studentsInit(students, 30);
        termsInit(terms, 9);
        studentTermsInit(studentTerms, students, terms, 3);

        GenerationOutput output = generator.generate(studentTerms, 3, 3);

        printOutputStats(output);
    }

    @Test
    public void compareAlgorithmsLarge(){
        double sumUnassigned1 = 0;
        double sumUnassigned2 = 0;
        double sumUnassigned3 = 0;

        //change 10 for a larger number to compare
        for (int i = 0; i < 10; i++) {
            List<Student> students = new ArrayList<>();
            List<Term> terms = new ArrayList<>();
            Map<Student, List<Term>> studentTerms = new HashMap<>();

            studentsInit(students, 30);
            termsInit(terms, 15);
            studentTermsInit(studentTerms, students, terms, 3);

            GenerationOutput output1 = generator.generate(studentTerms, 4, 1);

            GenerationOutput output2 = generator.generate(studentTerms, 4, 2);

            GenerationOutput output3 = generator.generate(studentTerms, 4, 3);

            sumUnassigned1 += output1.getUnassignedStudents().size();
            sumUnassigned2 += output2.getUnassignedStudents().size();
            sumUnassigned3 += output3.getUnassignedStudents().size();
        }

        System.out.println(sumUnassigned1/1000);
        System.out.println(sumUnassigned2/1000);
        System.out.println(sumUnassigned3/1000);
    }

    @Test
    public void compareAlgorithms(){
        List<Student> students = new ArrayList<>();
        List<Term> terms = new ArrayList<>();
        Map<Student, List<Term>> studentTerms = new HashMap<>();

        studentsInit(students, 30);
        termsInit(terms, 15);
        studentTermsInit(studentTerms, students, terms, 5);

        GenerationOutput output1 = generator.generate(studentTerms, 2, 1);

        GenerationOutput output2 = generator.generate(studentTerms, 2, 2);

        GenerationOutput output3 = generator.generate(studentTerms, 2, 3);

        System.out.println("First algorithm:");
        printOutputStats(output1);
        System.out.println("Second algorithm:");
        printOutputStats(output2);
        System.out.println("Combined algorithm:");
        printOutputStats(output3);
    }

    private void studentsInit(List<Student> students, int number){
        for (int i = 0; i < number; i++) {
            students.add(new Student(i));
        }
    }

    private void termsInit(List<Term> terms, int number){
        for (int i = 0; i < number; i++) {
            terms.add(new Term((long) i, Days.Friday, 0, null));
        }
    }

    private int randomGaussianInt(Random r, double mean, double deviation){
        return (int)(mean + r.nextGaussian() * deviation + 0.5);
    }

    private void studentTermsInit(Map<Student, List<Term>> studentTerms, List<Student> students, List<Term> terms,
                                  double averageStudentTerms){

        Random r = new Random();
        for (Student s : students) {
            List<Term> termsCopy = new ArrayList<>(terms);
            Collections.shuffle(termsCopy);
            studentTerms.put(s, termsCopy.subList(0, Math.max(randomGaussianInt(r, averageStudentTerms, 2), 0)));
        }
    }

    private void printOutputStats(GenerationOutput output){
        Set<Student> unassignedStudents = output.getUnassignedStudents();
        Map<Term, Set<Student>> termStudents = output.getTermStudents();

        System.out.println("Number of unassigned students: " + unassignedStudents.size());
        for (Map.Entry<Term, Set<Student>> entry : output.getTermStudents().entrySet()) {
            System.out.println("Group with " + entry.getValue().size() + " students");
        }
    }

    private void testOutput(GenerationOutput output, List<Student> students, Map<Student, List<Term>> studentTerms,
                            int groupNumber){
        Set<Student> unassignedStudents = output.getUnassignedStudents();
        Map<Term, Set<Student>> termStudents = output.getTermStudents();

        assertEquals(groupNumber, output.getTermStudents().keySet().size());

        for (Student student: students){
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
