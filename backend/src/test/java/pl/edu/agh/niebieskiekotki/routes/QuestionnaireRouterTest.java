package pl.edu.agh.niebieskiekotki.routes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;

public class QuestionnaireRouterTest extends AbstractRouterTest {
	@Override
	@BeforeEach
	public void setUp() {
		super.setUp();
	//	hibernateAdapter.clearDatabase();
	}

	@Test
	public void getProductsList() throws Exception {

		String uri = "/questionnaires";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.header("Auth-Token","eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqYW4ucHJvZmVzb3Jza2lAYWdoLmVkdS5wbCJ9.UDo9s4GiJzXDsPekwF6EMNQevup4NJT3Ns1rei09vhUrUajFvS2e8TJkpsPDjOtTBGV_oLoifwyA5i7FC1GHDg")
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();


		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();

		assertEquals("Should return status 200",200, status);
	}

	@Test
	public void getSpecificQuestionnaire() throws Exception {

		String uri = "/questionnaires/1";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.header("Auth-Token","eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqYW4ucHJvZmVzb3Jza2lAYWdoLmVkdS5wbCJ9.UDo9s4GiJzXDsPekwF6EMNQevup4NJT3Ns1rei09vhUrUajFvS2e8TJkpsPDjOtTBGV_oLoifwyA5i7FC1GHDg")
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();

		assertEquals("Should return status 200",200, status);

		uri = "/questionnaires/12333";

		mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.header("Auth-Token","eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqYW4ucHJvZmVzb3Jza2lAYWdoLmVkdS5wbCJ9.UDo9s4GiJzXDsPekwF6EMNQevup4NJT3Ns1rei09vhUrUajFvS2e8TJkpsPDjOtTBGV_oLoifwyA5i7FC1GHDg")
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		status = mvcResult.getResponse().getStatus();

		assertEquals("Should return status 404",404, status);
	}

	@Test
	public void addQuestionnaire() throws Exception {
		String uri = "/questionnaires";
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.post(uri)
						.header("Auth-Token","eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqYW4ucHJvZmVzb3Jza2lAYWdoLmVkdS5wbCJ9.UDo9s4GiJzXDsPekwF6EMNQevup4NJT3Ns1rei09vhUrUajFvS2e8TJkpsPDjOtTBGV_oLoifwyA5i7FC1GHDg")
						.content("""
						{
						    "expirationDate": "2022-04-16T20:34:34",
						    "label": "with terms",
						    "teacher_id":1,
						    "availableTerms":[1,2,3,4,5,11],
						    "autoSendingLinks":false,
						    "studentsInfo":[]
						}""".indent(4)).contentType("application/json")
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals("Should return status 200",200, status);
	}
}