package pl.edu.agh.niebieskiekotki;

import pl.edu.agh.niebieskiekotki.entitites.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class DataBaseMock {

    public static List<Timeslot> timeslots =
            Arrays.asList(
                    new Timeslot(0l, LocalTime.of(8, 0), LocalTime.of(9, 30)),
                    new Timeslot(1l, LocalTime.of(9, 35), LocalTime.of(11, 30)),
                    new Timeslot(2l, LocalTime.of(11, 15), LocalTime.of(12, 30)),
                    new Timeslot(3l, LocalTime.of(12, 50), LocalTime.of(14, 30)),
                    new Timeslot(4l, LocalTime.of(14, 40), LocalTime.of(16, 10)),
                    new Timeslot(5l, LocalTime.of(16, 15), LocalTime.of(17, 45)),
                    new Timeslot(6l, LocalTime.of(17, 50), LocalTime.of(19, 20))
            );

    public static List<Term> terms =
            Arrays.asList(
                    new Term(1l,0,0,timeslots.get(0)),
                    new Term(2l,0,0,timeslots.get(1)),
                    new Term(3l,0,0,timeslots.get(2)),
                    new Term(4l,0,0,timeslots.get(3)),
                    new Term(5l,0,0,timeslots.get(4)),
                    new Term(6l,0,0,timeslots.get(5)),
                    new Term(7l,0,0,timeslots.get(6)),

                    new Term(11l,1,0,timeslots.get(0)),
                    new Term(12l,1,0,timeslots.get(1)),
                    new Term(13l,1,0,timeslots.get(2)),
                    new Term(14l,1,0,timeslots.get(3)),
                    new Term(15l,1,0,timeslots.get(4)),
                    new Term(16l,1,0,timeslots.get(5)),
                    new Term(17l,1,0,timeslots.get(6)),

                    new Term(21l,2,0,timeslots.get(0)),
                    new Term(22l,2,0,timeslots.get(1)),
                    new Term(23l,2,0,timeslots.get(2)),
                    new Term(24l,2,0,timeslots.get(3)),
                    new Term(25l,2,0,timeslots.get(4)),
                    new Term(26l,2,0,timeslots.get(5)),
                    new Term(27l,2,0,timeslots.get(6)),

                    new Term(31l,3,0,timeslots.get(0)),
                    new Term(32l,3,0,timeslots.get(1)),
                    new Term(33l,3,0,timeslots.get(2)),
                    new Term(34l,3,0,timeslots.get(3)),
                    new Term(35l,3,0,timeslots.get(4)),
                    new Term(36l,3,0,timeslots.get(5)),
                    new Term(37l,3,0,timeslots.get(6)),

                    new Term(41l,4,0,timeslots.get(0)),
                    new Term(42l,4,0,timeslots.get(1)),
                    new Term(43l,4,0,timeslots.get(2)),
                    new Term(44l,4,0,timeslots.get(3)),
                    new Term(45l,4,0,timeslots.get(4)),
                    new Term(46l,4,0,timeslots.get(5)),
                    new Term(47l,4,0,timeslots.get(6))
            );


    public static List<Questionnaire> questionnaires = Arrays.asList(
            new Questionnaire(1l, LocalDateTime.of(2022,6,1,10,10),"First Questionnaire"),
            new Questionnaire(2l, LocalDateTime.of(2022,3,1,10,10),"Second Questionnaire"),
            new Questionnaire(3l, LocalDateTime.of(2025,6,1,10,10),"Third Questionnaire")
    );


    public static List<QuestionnaireTerm> questionnaireTerms = Arrays.asList(
            new QuestionnaireTerm( questionnaires.get(0), terms.get(0)),
            new QuestionnaireTerm( questionnaires.get(0), terms.get(31)),
            new QuestionnaireTerm( questionnaires.get(0), terms.get(32)),
            new QuestionnaireTerm( questionnaires.get(0), terms.get(12)),
            new QuestionnaireTerm( questionnaires.get(1), terms.get(6)),
            new QuestionnaireTerm( questionnaires.get(1), terms.get(31)),
            new QuestionnaireTerm( questionnaires.get(1), terms.get(10)),
            new QuestionnaireTerm( questionnaires.get(2), terms.get(15)),
            new QuestionnaireTerm( questionnaires.get(2), terms.get(22))
    );

    public static  List<Student> students = new ArrayList<>();


    public static List<Vote> votes = new ArrayList<>();





}
