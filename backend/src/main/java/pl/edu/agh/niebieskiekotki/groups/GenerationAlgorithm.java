package pl.edu.agh.niebieskiekotki.groups;

import org.jgrapht.alg.flow.DinicMFImpl;
import org.jgrapht.alg.flow.mincost.CapacityScalingMinimumCostFlow;
import org.jgrapht.alg.flow.mincost.MinimumCostFlowProblem;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.springframework.stereotype.Component;
import pl.edu.agh.niebieskiekotki.entitites.Student;
import pl.edu.agh.niebieskiekotki.entitites.Term;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class GenerationAlgorithm {

    public GenerationOutput generate(Map<Student, List<Term>> studentsTerms,
                                     Map<Student, List<Term>> studentsImpossibleTerms,
                                     int numGroups,
                                     int algorithmVersion, int groupSizeVariation){
        Set<Term> allTerms = getAllTerms(studentsTerms);
        int additionalTerms = additionalTermsFunction(numGroups);
        List<Term> candidateTerms = getCandidateTerms(studentsTerms, allTerms,
                numGroups + additionalTerms);

        int termIndexesLen = Math.min(numGroups, candidateTerms.size());

        List<Integer> termIndexes = new ArrayList<>();
        for (int i = 0; i < termIndexesLen; i++) {
            termIndexes.add(i);
        }

        GenerationOutput output = runCorrectAlgorithm(studentsTerms, algorithmVersion,
                candidateTerms, termIndexes, studentsImpossibleTerms, groupSizeVariation);

        while (createNextIndexes(termIndexes, candidateTerms.size())) {
            GenerationOutput newOutput = runCorrectAlgorithm(studentsTerms, algorithmVersion,
                    candidateTerms, termIndexes, studentsImpossibleTerms, groupSizeVariation);

            if (newOutput.getUnassignedStudents().size() < output.getUnassignedStudents().size()){
                output = newOutput;
            }
        }

        return output;
    }
    public GenerationOutput generate(Map<Student, List<Term>> studentsTerms,
                                     Map<Student, List<Term>> studentsImpossibleTerms,
                                     int numGroups,
                                     int algorithmVersion){
        return generate(studentsTerms, studentsImpossibleTerms, numGroups, algorithmVersion, 1);
    }

    private GenerationOutput runCorrectAlgorithm(Map<Student, List<Term>> studentsTerms, int algorithmVersion,
                                                 List<Term> candidateTerms, List<Integer> termIndexes,
                                                 Map<Student, List<Term>> studentsImpossibleTerms,
                                                 int groupSizeVariation){
        if (algorithmVersion == 1) {
            return divideForTerms(studentsTerms, chooseTerms(candidateTerms, termIndexes));
        } else if (algorithmVersion == 2) {
            return divideForTermsSecondAlgorithm(studentsTerms, chooseTerms(candidateTerms, termIndexes),
                    studentsImpossibleTerms, groupSizeVariation);
        } else if (algorithmVersion == 3) {
            GenerationOutput output1 = divideForTerms(studentsTerms, chooseTerms(candidateTerms, termIndexes));
            GenerationOutput output2 = divideForTermsSecondAlgorithm(studentsTerms, chooseTerms(candidateTerms, termIndexes),
                    studentsImpossibleTerms, groupSizeVariation);
            if (rateOutput(output1) < rateOutput(output2)){
                return output1;
            }
            return output2;
        } else {
            return divideForTerms(studentsTerms, chooseTerms(candidateTerms, termIndexes));
        }
    }

    private double rateOutput(GenerationOutput output){
        int minimal = 100000;
        int maximal = 0;

        double result = 0;
        for(Map.Entry<Term, Set<Student>> entry : output.getTermStudents().entrySet()){
            if (entry.getValue().size() > maximal){
                maximal = entry.getValue().size();
            }
            if (entry.getValue().size() < minimal){
                if (entry.getValue().size() != 0) {
                    minimal = entry.getValue().size();
                } else {
                    result += 1;
                }
            }

        }
        return output.getUnassignedStudents().size() * 2 + result * 2 + maximal - minimal;
    }

    private Set<Term> chooseTerms(List<Term> terms, List<Integer> indexes){
        Set<Term> result = new HashSet<>();
        //System.out.println(terms.size());
        for (Integer i : indexes) {
            result.add(terms.get(i));
        }
        return result;
    }

    private boolean createNextIndexes(List<Integer> indexes, int length){
        int i = 1;
        while (indexes.get(indexes.size() - i) == length - i){
            i++;
            if (indexes.size() - i <= 0) {
                return false;
            }
        }

        int movedIndexVal = indexes.get(indexes.size() - i) + 1;
        indexes.set(indexes.size() - i, movedIndexVal);
        for (int j = 0; j < i; j++){
            indexes.set(indexes.size() - i + j, movedIndexVal + j);
        }
        return true;

    }


    private int additionalTermsFunction(int numGroups){
        //return 0;
        if (numGroups > 15){
            return 2;
        } else if (numGroups > 8){
            return 4;
        }
        return 6;
    }

    private GenerationOutput divideForTermsSecondAlgorithm(Map<Student, List<Term>> studentsTerms, Set<Term> terms,
                                                           Map<Student, List<Term>> studentsImpossibleTerms,
                                                           int groupSizeVariation){
        Set<Student> allStudents = studentsTerms.keySet();
        int averageGroupSize = (int) Math.round(((double) allStudents.size() / terms.size()));
        int maxGroupSize = averageGroupSize + groupSizeVariation;
        int minGroupSize = averageGroupSize - groupSizeVariation;
        //int minGroupSize = 0;
        var network = new DefaultDirectedWeightedGraph<>(DefaultWeightedEdge.class);
        var destination = new Object();

        Map<Object, Integer> supplyDict = new HashMap<>();

        Map<DefaultWeightedEdge, Integer> capacityDict = new HashMap<>();
        Map<DefaultWeightedEdge, Integer> minCapacityDict = new HashMap<>();

        network.addVertex(destination);
        supplyDict.put(destination, -1 * allStudents.size());

        for (var term : terms){
            network.addVertex(term);
            supplyDict.put(term, 0);
            network.addEdge(term, destination);
            capacityDict.put(network.getEdge(term, destination), maxGroupSize);
            minCapacityDict.put(network.getEdge(term, destination), minGroupSize);
            network.setEdgeWeight(term, destination, 0);
        }

        var unassigned = new Object();
        network.addVertex(unassigned);
        supplyDict.put(unassigned, 0);
        network.addEdge(unassigned, destination);
        capacityDict.put(network.getEdge(unassigned, destination), 2000);
        minCapacityDict.put(network.getEdge(unassigned, destination), 0);
        network.setEdgeWeight(unassigned, destination, 0);

        for (var student: allStudents){
            network.addVertex(student);
            supplyDict.put(student, 1);
//            for (var term : studentsTerms.get(student)) {
//                if (network.containsVertex(term)) {
//                    network.addEdge(student, term);
//                    capacityDict.put(network.getEdge(student, term), 1);
//                    minCapacityDict.put(network.getEdge(student, term), 0);
//                    network.setEdgeWeight(student, term, -500);
//                }
//            }
            for (Term term : terms){
                if (studentsTerms.get(student).contains(term)){
                    network.addEdge(student, term);
                    capacityDict.put(network.getEdge(student, term), 1);
                    minCapacityDict.put(network.getEdge(student, term), 0);
                    network.setEdgeWeight(student, term, -100);
                } else if (!studentsImpossibleTerms.get(student).contains(term)) {
                    network.addEdge(student, term);
                    capacityDict.put(network.getEdge(student, term), 1);
                    minCapacityDict.put(network.getEdge(student, term), 0);
                    network.setEdgeWeight(student, term, -2);
                }
            }
            network.addEdge(student, unassigned);
            capacityDict.put(network.getEdge(student, unassigned), 1);
            minCapacityDict.put(network.getEdge(student, unassigned), 0);
            network.setEdgeWeight(student, unassigned, -1);
        }

        var problem = new MinimumCostFlowProblem.MinimumCostFlowProblemImpl<>(network,
                supplyDict::get, capacityDict::get, minCapacityDict::get);

        var algorithm = new CapacityScalingMinimumCostFlow<Object, DefaultWeightedEdge>();

        algorithm.getMinimumCostFlow(problem);

        Map<DefaultWeightedEdge, Double> flowMap = algorithm.getFlowMap();

        GenerationOutput output = new GenerationOutput(terms);

//        for (Map.Entry<Student, List<Term>> entry : studentsTerms.entrySet()) {
//            Student student = entry.getKey();
//            for (Term term : entry.getValue()) {
//                if (flowMap.getOrDefault(network.getEdge(student, term), 0.0) > 0){
//                    //System.out.println("Student " + student.getId() + " goes to group " + term.getId());
//                    output.getTermStudents().get(term).add(student);
//                }
//            }
//            if (flowMap.getOrDefault(network.getEdge(student, unassigned), 0.0) > 0){
//                output.getUnassignedStudents().add(student);
//            }
//        }

        for (var student: allStudents){
            for (Term term : terms){
                if (flowMap.getOrDefault(network.getEdge(student, term), 0.0) > 0){
                    //System.out.println("Student " + student.getId() + " goes to group " + term.getId());
                    output.getTermStudents().get(term).add(student);
                }
            }
            if (flowMap.getOrDefault(network.getEdge(student, unassigned), 0.0) > 0){
                output.getUnassignedStudents().add(student);
            }
        }
        return output;
    }

    private GenerationOutput divideForTerms(Map<Student, List<Term>> studentsTerms, Set<Term> terms){
        //System.out.println("Starting division");

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

        for (var term: terms){
            network.addVertex(term);
        }

        for (var student: allStudents){
            for (var term: studentsTerms.get(student)){
                if (terms.contains(term)) {
                    network.addEdge(student, term);
                }
            }
        }

        double maxGroupSize = Math.round(((double) allStudents.size() / terms.size() + 0.5));

        for (var term: terms){
            network.addVertex(term);
            network.addEdge(term, sink);
            network.setEdgeWeight(term, sink, maxGroupSize);
        }

        // find max flow using Dinic's algorithm
        var algorithm = new DinicMFImpl<>(network);
        var flow = algorithm.getMaximumFlow(source, sink);

        GenerationOutput output = new GenerationOutput(terms);

        for (var edge : network.edgeSet()){
            Object edgeSource = network.getEdgeSource(edge);
            Object edgeTarget = network.getEdgeTarget(edge);

            if (edgeSource == source
                    && edgeTarget instanceof Student student
                    && flow.getFlow(edge) < 0.1)
            {
                output.addUnassignedStudent(student);
            }

            else if (edgeSource instanceof Student student
                    && edgeTarget instanceof Term term
                    && flow.getFlow(edge) > 0.9)
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
    private List<Term> getCandidateTerms(Map<Student, List<Term>> studentsTerms, Set<Term> allTerms, int n){
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

        return termList.stream().limit(n).collect(Collectors.toList());
    }

    private Set<Term> getAllTerms(Map<Student, List<Term>> studentsTerms){
        Set<Term> allTerms = new HashSet<>();
        for (List<Term> terms: studentsTerms.values()){
            allTerms.addAll(terms);
        }
        return allTerms;
    }

}
