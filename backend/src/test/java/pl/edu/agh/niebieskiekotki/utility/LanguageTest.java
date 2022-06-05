package pl.edu.agh.niebieskiekotki.utility;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Profile;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LanguageTest {
    @Test
    public void fromStringTest() {
        assertThat(Language.fromString("polish")).isEqualTo(Language.POLISH);
        assertThat(Language.fromString("english")).isEqualTo(Language.ENGLISH);
        assertThat(Language.fromString("pl")).isNotEqualTo(Language.POLISH);
        assertThat(Language.fromString("en")).isEqualTo(Language.ENGLISH);
        assertThat(Language.fromString("unsupportedLanguage")).isEqualTo(Language.ENGLISH);
    }
}
