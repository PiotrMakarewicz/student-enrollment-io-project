package pl.edu.agh.niebieskiekotki.entitites;

import javax.persistence.*;

@Entity
public class Term {
    @Id
    @GeneratedValue
    private Long id;

    private int day;
    private int week;

    public Term(Long id, int day, int week, Timeslot timeslot) {
        this.id = id;
        this.day = day;
        this.week = week;
        this.timeslot = timeslot;
    }

    @ManyToOne
    @JoinColumn(name = "timeslot_id")
    private Timeslot timeslot;

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

    public String getShortLabel(){
        return switch (day) {
            case 0 -> "Pon " + timeslot;
            case 1 -> "Wt " + timeslot;
            case 2 -> "Śr " + timeslot;
            case 3 -> "Czw " + timeslot;
            case 4 -> "Pt " + timeslot;
            default -> "";
        };

    }
}
