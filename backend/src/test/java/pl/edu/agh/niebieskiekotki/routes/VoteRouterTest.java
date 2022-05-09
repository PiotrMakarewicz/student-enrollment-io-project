package pl.edu.agh.niebieskiekotki.routes;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.edu.agh.niebieskiekotki.entitites.Questionnaire;

import static org.junit.Assert.assertEquals;

public class VoteRouterTest extends AbstractRouterTest {
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
    public void addQuestionnaire() throws Exception {
        String uri = "/vote";
        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders.post(uri)
                        .content("{\n" +
                                "    \"questionnaire_id\":1,\n" +
                                "    \"firstName\":\"Adam\",\n" +
                                "    \"lastName\":\"Kowalski\",\n" +
                                "    \"emailAddress\":\"a@a.pl\",\n" +
                                "    \"indexNumber\":1234569,\n" +
                                "    \"selected_terms\":[1,2,3]\n" +
                                "}").contentType("application/json")
                        .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals("Should return status 200",200, status);
    }

    @Test
    public void getVotes() throws Exception {
        String uri = "/vote/1";
        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders.get(uri)
                        .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals("Should return status 200",200, status);
    }
}