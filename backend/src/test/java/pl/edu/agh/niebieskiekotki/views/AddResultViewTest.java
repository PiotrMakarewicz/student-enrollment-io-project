package pl.edu.agh.niebieskiekotki.views;

import org.apache.commons.math3.analysis.function.Add;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Profile;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AddResultViewTest {
    @Test
    void toStringTest() {
        AddResultView view = new AddResultView(1L, 1L, 1L);
        assertThat(view.toString()).isEqualTo("AddResultView{" +
                "studentId=" + 1L +
                ", questionnaireId=" + 1L +
                ", termId=" + 1L +
                '}');
        assertThat(view.toString()).isNotEqualTo("literally anything else");
        assertThat(view.toString()).isNotEqualTo("AddResultView{" +
                "studentId=" + 0L +
                ", questionnaireId=" + 1L +
                ", termId=" + 1L +
                '}');
    }
}
