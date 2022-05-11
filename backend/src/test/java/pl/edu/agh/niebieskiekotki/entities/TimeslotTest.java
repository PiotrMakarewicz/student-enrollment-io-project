package pl.edu.agh.niebieskiekotki.entities;

import org.junit.jupiter.api.Test;
import pl.edu.agh.niebieskiekotki.entitites.Timeslot;

import java.time.LocalTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TimeslotTest {
    @Test
    public void toStringTest() {
        assertThat(new Timeslot(1L, LocalTime.of(0, 0), LocalTime.of(0, 0)).toString()).isEqualTo("0:00 - 0:00");
        assertThat(new Timeslot(1L, LocalTime.of(8, 0), LocalTime.of(9, 30)).toString()).isEqualTo("8:00 - 9:30");
        assertThat(new Timeslot(1L, LocalTime.of(15, 0), LocalTime.of(15, 1)).toString()).isEqualTo("15:00 - 15:01");
        assertThat(new Timeslot(1L, LocalTime.of(23, 0), LocalTime.of(23, 10)).toString()).isEqualTo("23:00 - 23:10");
        assertThat(new Timeslot(1L, LocalTime.of(23, 59), LocalTime.of(1, 9)).toString()).isEqualTo("23:59 - 1:09");
    }
}
