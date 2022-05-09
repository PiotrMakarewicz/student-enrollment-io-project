package pl.edu.agh.niebieskiekotki.routes;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.edu.agh.niebieskiekotki.entitites.Questionnaire;

import static org.junit.Assert.assertEquals;

public class TermRouterTest extends AbstractRouterTest {
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @BeforeAll
    public void clearDatabase(){
        hibernateAdapter.deleteAll(Questionnaire.class);
        System.out.println("Clear database");
    }

    @Test
    public void getTerms() throws Exception {
        String uri = "/terms";
        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders.get(uri)
                        .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals("Should return status 200",200, status);
    }
}