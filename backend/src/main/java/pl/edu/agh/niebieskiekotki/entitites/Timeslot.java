package pl.edu.agh.niebieskiekotki.entitites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class Timeslot {

    @Id
    @GeneratedValue
    private Long id;

    private LocalTime startTime;
    private LocalTime endTime;

    public Timeslot(Long id, LocalTime startTime, LocalTime endTime) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Timeslot() {

    }

    @Override
    public String toString() {
        return this.startTime.getHour() + ":" + startTime.getMinute() +
                " - " + this.endTime.getHour() + ":" + endTime.getMinute() ;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
}
