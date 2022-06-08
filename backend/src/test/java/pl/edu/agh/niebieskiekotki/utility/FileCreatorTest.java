package pl.edu.agh.niebieskiekotki.utility;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Profile;
import pl.edu.agh.niebieskiekotki.entitites.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class FileCreatorTest {
    @Test
    public void createFileGroupsTest() {
        List<Results> results = new ArrayList<>();

        Questionnaire questionnaire1 = new Questionnaire();
        questionnaire1.setId(1L);

        Student[] students = new Student[]{new Student("a", "b", "a@com.pl", 111111),
                new Student("c", "d", "b@com.pl", 222222),
                new Student("e", "f", "c@com.pl", 333333)};

        Term term1 = new Term();
        term1.setDay(Days.Monday);
        term1.setTimeslot(new Timeslot(1L, LocalTime.of(8, 0), LocalTime.of(9, 30)));
        Term term2 = new Term();
        term2.setDay(Days.Monday);
        term2.setTimeslot(new Timeslot(1L, LocalTime.of(9, 35), LocalTime.of(11, 5)));
        term2.setId(2L);

        results.add(new Results(questionnaire1, term1, students[0]));
        results.add(new Results(questionnaire1, term1, students[1]));
        results.add(new Results(questionnaire1, term2, students[2]));

        String f1 = "test_file_f1.txt";
        String f2 = "test_file_f2.txt";
        String f3 = "test_file_f3.txt";
        String f4 = "test_file_f4.txt";
        String f5 = "test_file_f5.txt";

        File output1en = null;
        File output1pl = null;
        File output1un = null;
        File noName = null;


        try {
            output1en = FileCreator.createFileGroups(results, Language.POLISH, f1);
            output1pl = FileCreator.createFileGroups(results, Language.ENGLISH, f2);
            output1un = FileCreator.createFileGroups(results, null, f3);

            assertThatThrownBy(() -> FileCreator.createFileGroups(null, null, f4)).isInstanceOf(IllegalArgumentException.class);
            assertThatThrownBy(() -> FileCreator.createFileGroups(new ArrayList<>(), null, f5)).isInstanceOf(IllegalArgumentException.class);
            noName = FileCreator.createFileGroups(results, null, null);
            assertThat(noName.getName()).isEqualTo("groups.txt");
            assertThat(Files.readString(Paths.get(f1))).isEqualTo(
                    """
                    Pon 8:00 - 9:30
                    a b
                    c d


                    Pon 9:35 - 11:05
                    e f
                    """
            );
            assertThat(Files.readString(Paths.get(f2))).isEqualTo(
                    """
                    Mon 8:00 - 9:30
                    a b
                    c d


                    Mon 9:35 - 11:05
                    e f
                    """
            );
            assertThat(Files.readString(Paths.get(f3))).isEqualTo(
                    """
                    Mon 8:00 - 9:30
                    a b
                    c d


                    Mon 9:35 - 11:05
                    e f
                    """
            );
            assertThat(Files.readString(Paths.get(f3))).isNotEqualTo(
                    """
                    Mon 8:00 - 9:30
                    a b
                    c d
                    Mon 9:35 - 11:05
                    e f
                    """
            );
            assertThat(Files.readString(Paths.get(f1))).isNotEqualTo(
                    ""
            );
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (output1en != null) {
                output1en.delete();
            }
            if (output1pl != null) {
                output1pl.delete();
            }
            if (output1un != null) {
                output1un.delete();
            }
            if (noName != null) {
                noName.delete();
            }
        }
    }
}
