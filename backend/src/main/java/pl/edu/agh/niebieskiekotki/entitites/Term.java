package pl.edu.agh.niebieskiekotki.entitites;

import pl.edu.agh.niebieskiekotki.utility.Days;
import pl.edu.agh.niebieskiekotki.utility.Language;

import javax.persistence.*;

@Entity
public class Term implements Comparable<Term>{
    @Id
    @GeneratedValue
    private Long id;

    private Days day;
    private int week;
    @ManyToOne
    @JoinColumn(name = "timeslot_id")
    private Timeslot timeslot;

    public Term(Long id, Days day, int week, Timeslot timeslot) {
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

    public Days getDay() {
        return day;
    }

    public void setDay(Days day) {
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
            case Monday -> "Monday " + timeslot;
            case Tuesday -> "Tuesday " + timeslot;
            case Wednesday -> "Wednesday " + timeslot;
            case Thursday -> "Thursday " + timeslot;
            case Friday -> "Friday " + timeslot;
            default -> "";
        };

    }

    public String getShortLabel(Language language){
        if (language == Language.POLISH) {
            return switch (day) {
                case Monday -> "Pon " + timeslot;
                case Tuesday -> "Wt " + timeslot;
                case Wednesday -> "Śr " + timeslot;
                case Thursday -> "Czw " + timeslot;
                case  Friday -> "Pt " + timeslot;
                default -> "";
            };
        } else {
            return switch (day) {
                case Monday -> "Mon " + timeslot;
                case Tuesday -> "Tue " + timeslot;
                case Wednesday -> "Wed " + timeslot;
                case Thursday -> "Thu " + timeslot;
                case Friday -> "Fri " + timeslot;
                default -> "";
            };
        }
    }

    public int compareTo(Term other){
        if (other.day == day){
            return timeslot.compareTo(other.timeslot);
        }
        return day.ordinal() - other.day.ordinal();
    }
}
