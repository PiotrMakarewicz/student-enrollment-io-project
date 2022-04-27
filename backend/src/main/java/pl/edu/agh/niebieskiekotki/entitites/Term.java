package pl.edu.agh.niebieskiekotki.entitites;

import javax.persistence.*;

@Entity
public class Term {
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

        switch (day) {
            case 0:
                return "Monday " + timeslot;
            case 1:
                return "Tuesday " + timeslot;
            case 2:
                return "Wednesday " + timeslot;
            case 3:
                return "Thursday " + timeslot;
            case 4:
                return "Friday " + timeslot;
        }

        return "";
    }
}
