package pl.edu.agh.niebieskiekotki;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pl.edu.agh.niebieskiekotki.entitites.Questionnaire;
import pl.edu.agh.niebieskiekotki.routes.QuestionnaireRouter;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class QuestionnaireRouterTest {





    QuestionnaireRouter questionnaireRouter;

    public QuestionnaireRouterTest(QuestionnaireRouter questionnaireRouter) {
        this.questionnaireRouter = questionnaireRouter;
    }

    @Test
    public void givenEmployees_whenGetEmployees_thenReturnJsonArray()
            throws Exception {

        assertTrue(questionnaireRouter.GetAll() instanceof java.util.List<Questionnaire>);
    }


}