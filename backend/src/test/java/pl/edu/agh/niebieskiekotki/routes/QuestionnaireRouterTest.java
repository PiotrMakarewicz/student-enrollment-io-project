package pl.edu.agh.niebieskiekotki.routes;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;


public class QuestionnaireRouterTest extends AbstractRouterTest {
	@Override
	@Before
	public void setUp() {
		super.setUp();
	//	hibernateAdapter.clearDatabase();
	}

	@Test
	public void getProductsList() throws Exception {

		String uri = "/questionnaires";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();


		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();

		assertEquals("Should return status 200",200, status);
	}

	@Test
	public void getSpecificQuestionnaire() throws Exception {

		String uri = "/questionnaires/1";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();

		assertEquals("Should return status 200",200, status);

		uri = "/questionnaires/12333";

		mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		status = mvcResult.getResponse().getStatus();

		assertEquals("Should return status 404",404, status);
	}

	@Test
	public void addQuestionnaire() throws Exception {
		String uri = "/questionnaires";
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.post(uri)
				.content("    {\n" +
						"        \"expirationDate\": \"2022-04-16T20:34:34\",\n" +
						"        \"label\": \"with terms\",\n" +
						"        \"teacher_id\":1,\n" +
						"        \"availableTerms\":[1,2,3,4,5,11],\n" +
						"        \"autoSendingLinks\":false,\n" +
						"        \"studentsInfo\":[]\n" +
						"    }").contentType("application/json")
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals("Should return status 200",200, status);
	}
}