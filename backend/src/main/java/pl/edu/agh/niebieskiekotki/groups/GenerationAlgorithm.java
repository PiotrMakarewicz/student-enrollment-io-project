package pl.edu.agh.niebieskiekotki.groups;

import org.jgrapht.alg.flow.DinicMFImpl;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.springframework.stereotype.Component;
import pl.edu.agh.niebieskiekotki.entitites.Student;
import pl.edu.agh.niebieskiekotki.entitites.Term;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class GenerationAlgorithm {
    public GenerationOutput generate(Map<Student, List<Term>> studentsTerms, int numGroups){
        var network = new DefaultDirectedWeightedGraph<>(DefaultWeightedEdge.class);
        var source = new Object();
        var sink = new Object();

        network.addVertex(source);
        network.addVertex(sink);

        Set<Student> allStudents = studentsTerms.keySet();

        for (var student: allStudents){
            network.addVertex(student);
            network.addEdge(source, student);
        }

        Set<Term> allTerms = getAllTerms(studentsTerms);
        Set<Term> candidateTerms = getCandidateTerms(studentsTerms, allTerms, numGroups);

        for (var term: candidateTerms){
            network.addVertex(term);
        }

        for (var student: allStudents){
            for (var term: studentsTerms.get(student)){
                if (candidateTerms.contains(term)) {
                    network.addEdge(student, term);
                }
            }
        }

        double maxGroupSize = Math.round(((double) allStudents.size() / numGroups) + 0.5);

        for (var term: candidateTerms){
            network.addVertex(term);
            network.addEdge(term, sink);
            network.setEdgeWeight(term, sink, maxGroupSize);
        }

        // find max flow using Dinic's algorithm
        var algorithm = new DinicMFImpl<>(network);
        var flow = algorithm.getMaximumFlow(source, sink);

        GenerationOutput output = new GenerationOutput(candidateTerms);

        for (var edge : network.edgeSet()){
            Object edgeSource = network.getEdgeSource(edge);
            Object edgeTarget = network.getEdgeTarget(edge);

            if (edgeSource == source
                    && edgeTarget instanceof Student student
                    && flow.getFlow(edge) < 0.1
            )
            {
                output.addUnassignedStudent(student);
            }

            else if (edgeSource instanceof Student student
                    && edgeTarget instanceof Term term
                    && flow.getFlow(edge) > 0.9
            )
            {
                output.getTermStudents().get(term).add(student);
            }
        }
        return output;
    }

    /**
     * Picks n candidate terms for assigning students.
     * Terms with the highest number of points are elected.
     * Each term gets 1/k points for a student who selected this terms if the student selected k terms in total.
     */
    private Set<Term> getCandidateTerms(Map<Student, List<Term>> studentsTerms, Set<Term> allTerms, int n){
        Map<Term, Double> termPoints = new HashMap<>();

        for (Term term: allTerms){
            termPoints.put(term, 0.0);
        }

        for (Student student: studentsTerms.keySet()){
            double k = studentsTerms.get(student).size();
            for (Term term: studentsTerms.get(student)){
                termPoints.put(term, termPoints.get(term) + 1.0 / k);
            }
        }

        List<Term> termList = new ArrayList<>(allTerms);
        termList.sort((t1, t2) -> {
            if (termPoints.get(t1) < termPoints.get(t2))
                return -1;
            else if (Objects.equals(termPoints.get(t1), termPoints.get(t2)))
                return 0;
            else
                return 1;
        });
        Collections.reverse(termList);

        return termList.stream().limit(n).collect(Collectors.toSet());
    }

    private Set<Term> getAllTerms(Map<Student, List<Term>> studentsTerms){
        Set<Term> allTerms = new HashSet<>();
        for (List<Term> terms: studentsTerms.values()){
            allTerms.addAll(terms);
        }
        return allTerms;
    }

}
