package pl.edu.agh.niebieskiekotki.routes;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;


public class FileRouterTest extends AbstractRouterTest{
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void getPreferences() throws Exception {
//        String uri = "/files/preferences/english/32";
//        MvcResult mvcResult = mvc
//                .perform(MockMvcRequestBuilders.get(uri)
//                        .accept(MediaType.ALL_VALUE)).andReturn();
//
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals("Should return status 200",200, status);
    }
}
