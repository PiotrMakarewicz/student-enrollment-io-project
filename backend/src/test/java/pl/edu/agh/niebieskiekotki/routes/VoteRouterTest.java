package pl.edu.agh.niebieskiekotki.routes;


import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;

public class VoteRouterTest extends AbstractRouterTest {
    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
    }


    @Test
    public void addQuestionnaire() throws Exception {
        String uri = "/vote";
        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders.post(uri)
                        .content("""
                                {
                                    "questionnaireId":1,
                                    "firstName":"Adam",
                                    "lastName":"Kowalski",
                                    "emailAddress":"a@a.pl",
                                    "indexNumber":1234569,
                                    "selectedTerms":[1,2,3]
                                }""").contentType("application/json")
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