package pl.edu.agh.niebieskiekotki.groups;

import pl.edu.agh.niebieskiekotki.entitites.Student;
import pl.edu.agh.niebieskiekotki.entitites.Term;

import java.util.*;

public class GenerationOutput {

    private final Map<Term, Set<Student>> termStudents = new HashMap<>();
    private final Set<Student> unassignedStudents = new HashSet<>();

    public GenerationOutput(Set<Term> terms){
        for (Term term : terms){
            termStudents.put(term, new HashSet<>());
        }
    }

    public Set<Student> getUnassignedStudents() {
        return unassignedStudents;
    }

    public void addUnassignedStudent(Student student){
        unassignedStudents.add(student);
    }

    public Map<Term, Set<Student>> getTermStudents() {
        return termStudents;
    }

    @Override
    public String toString() {
        return "GenerationOutput{" +
                "termStudents=" + termStudents +
                ", unassignedStudents=" + unassignedStudents +
                '}';
    }
}
