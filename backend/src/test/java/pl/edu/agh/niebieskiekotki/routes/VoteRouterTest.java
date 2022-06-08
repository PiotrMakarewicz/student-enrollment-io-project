package pl.edu.agh.niebieskiekotki.routes;


import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Profile;
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
    public void addVote() throws Exception {

        String hash = "d6b5915c46057bcb005f46f6433df65609dd3a7a57af75ac1a5a4a7c299ebffb";
        String uri = "/vote/"+hash;
        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders.post(uri)
                        .content("""
                                {
                                    "selectedTerms":[1,2,3],
                                    "impossibleTerms":{"37": "kokoro", "47": "no tsumi"}
                                }""").contentType("application/json")
                        .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals("Should return status 200",200, status);
    }

    @Test
    public void getVotes() throws Exception {
        String uri = "/votes/1";
        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders.get(uri)
                        .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals("Should return status 200",200, status);
    }
}