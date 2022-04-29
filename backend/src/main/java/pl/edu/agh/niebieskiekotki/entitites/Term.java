package pl.edu.agh.niebieskiekotki.entitites;

import pl.edu.agh.niebieskiekotki.utility.Language;

import javax.persistence.*;

@Entity
public class Term implements Comparable<Term>{
    @Id
    @GeneratedValue
    private Long id;

    private int day;
    private int week;
    @ManyToOne
    @JoinColumn(name = "timeslot_id")
    private Timeslot timeslot;

    public Term(Long id, int day, int week, Timeslot timeslot) {
        this.id = id;
        this.day = day;
        this.week = week;
        this.timeslot = timeslot;
    }

    public Term() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public Timeslot getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(Timeslot timeslot) {
        this.timeslot = timeslot;
    }

    @Override
    public String toString() {
        return switch (day) {
            case 0 -> "Monday " + timeslot;
            case 1 -> "Tuesday " + timeslot;
            case 2 -> "Wednesday " + timeslot;
            case 3 -> "Thursday " + timeslot;
            case 4 -> "Friday " + timeslot;
            default -> "";
        };

    }

    public String getShortLabel(Language language){
        if (language == Language.POLISH) {
            return switch (day) {
                case 0 -> "Pon " + timeslot;
                case 1 -> "Wt " + timeslot;
                case 2 -> "Śr " + timeslot;
                case 3 -> "Czw " + timeslot;
                case 4 -> "Pt " + timeslot;
                default -> "";
            };
        } else {
            return switch (day) {
                case 0 -> "Mon " + timeslot;
                case 1 -> "Tue " + timeslot;
                case 2 -> "Wed " + timeslot;
                case 3 -> "Thu " + timeslot;
                case 4 -> "Fri " + timeslot;
                default -> "";
            };
        }
    }

    public int compareTo(Term other){
        if (other.day == day){
            return timeslot.compareTo(other.timeslot);
        }
        return day - other.day;
    }
}
