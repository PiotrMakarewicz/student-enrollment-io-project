package pl.edu.agh.niebieskiekotki.entities;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Profile;
import pl.edu.agh.niebieskiekotki.entitites.Term;
import pl.edu.agh.niebieskiekotki.entitites.Timeslot;
import pl.edu.agh.niebieskiekotki.utility.Days;
import pl.edu.agh.niebieskiekotki.utility.Language;

import java.time.LocalTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
public class TermTest {
    @Test
    public void toStringTest() {
        assertThat(new Term(1L, Days.Monday, 1, new Timeslot(1L, LocalTime.of(23, 59), LocalTime.of(1, 9))).toString()).isEqualTo("Monday 23:59 - 1:09");
        assertThat(new Term(1L, Days.Friday, 1, new Timeslot(1L, LocalTime.of(12, 2), LocalTime.of(13, 11))).toString()).isEqualTo("Friday 12:02 - 13:11");
        assertThat(new Term(1L, Days.Wednesday, 1, new Timeslot(1L, LocalTime.of(22, 7), LocalTime.of(0, 59))).toString()).isEqualTo("Wednesday 22:07 - 0:59");
    }

    @Test
    public void getShortLabelTest() {
        assertThat(new Term(1L, Days.Tuesday, 1, new Timeslot(1L, LocalTime.of(23, 59), LocalTime.of(1, 9))).getShortLabel(Language.ENGLISH)).isEqualTo("Tue 23:59 - 1:09");
        assertThat(new Term(1L, Days.Wednesday, 1, new Timeslot(1L, LocalTime.of(15, 39), LocalTime.of(17, 40))).getShortLabel(Language.POLISH)).isEqualTo("Śr 15:39 - 17:40");
        assertThat(new Term(1L, Days.Thursday, 1, new Timeslot(1L, LocalTime.of(15, 39), LocalTime.of(17, 40))).getShortLabel(Language.POLISH)).isEqualTo("Czw 15:39 - 17:40");
        assertThat(new Term(1L, Days.Thursday, 1, new Timeslot(1L, LocalTime.of(15, 39), LocalTime.of(17, 40))).getShortLabel(null)).isEqualTo("Thu 15:39 - 17:40");
    }

    @Test
    public void compareToTest() {
        assertThat(new Term(1L, Days.Tuesday, 1, new Timeslot(1L, LocalTime.of(23, 59), LocalTime.of(1, 9))).compareTo(new Term(1L, Days.Thursday, 1, new Timeslot(1L, LocalTime.of(23, 59), LocalTime.of(1, 9))))).isEqualTo(-2);
        assertThat(new Term(1L, Days.Tuesday, 1, new Timeslot(1L, LocalTime.of(23, 59), LocalTime.of(1, 9))).compareTo(new Term(1L, Days.Monday, 1, new Timeslot(1L, LocalTime.of(23, 59), LocalTime.of(1, 9))))).isEqualTo(1);
        assertThat(new Term(1L, Days.Tuesday, 1, new Timeslot(1L, LocalTime.of(23, 59), LocalTime.of(1, 9))).compareTo(new Term(1L, Days.Tuesday, 1, new Timeslot(1L, LocalTime.of(23, 59), LocalTime.of(1, 9))))).isEqualTo(0);
        assertThat(new Term(1L, Days.Friday, 1, new Timeslot(1L, LocalTime.of(23, 59), LocalTime.of(1, 9))).compareTo(new Term(1L, Days.Monday, 1, new Timeslot(1L, LocalTime.of(23, 59), LocalTime.of(1, 9))))).isEqualTo(4);
        assertThat(new Term(1L, Days.Friday, 1, new Timeslot(1L, LocalTime.of(0, 0), LocalTime.of(1, 9))).compareTo(new Term(1L, Days.Friday, 1, new Timeslot(1L, LocalTime.of(23, 59), LocalTime.of(1, 9))))).isNotEqualTo(0);

    }
}
