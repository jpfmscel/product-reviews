package com.adidas.reviewservice.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsNot;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.adidas.reviewservice.ReviewServiceApplication;
import com.adidas.reviewservice.dto.GenericResponse;
import com.adidas.reviewservice.entities.Review;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReviewServiceApplication.class, webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class ReviewAPI_IT implements RestAPIIntegrationTest {

	private static final String API_PATH = "/api/review";

	@Autowired
	private MockMvc mvc;

	String port = "8083";
	String BASE_URL = "http://localhost:";
	Review review;

	@Autowired
	ObjectMapper objMapper;

	@Before
	public void setUp() {
		BASE_URL += port;
		if (review == null) {
			review = Review.builder().title("Title TEST").description("Description TEST").productId("123").score(3)
					.build();
		}
	}

	@Test
	public void shouldInsertReviewAndReturnStatusCode_200() throws Exception {

		mvc.perform(MockMvcRequestBuilders.get(API_PATH).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
//				.andExpect(MockMvcResultMatchers.jsonPath("$.result", IsNot.not(null)));

//		GenericResponse postResponse = post(BASE_URL + API_PATH, review);
//		HashMap result = objMapper.readValue(postResponse.getResult().toString(), HashMap.class);
//		String id = (String) result.get("id");
//		assertEquals(postResponse.getCode().intValue(), 200);
//		assertNotNull(result);
//		assertNotNull(id);
//		review.setId(id);
	}

//	@Test
	public void shouldUpdateReviewAndReturnStatusCode_200() {

	}

//	@Test
	public void shouldFindReviewAndReturnStatusCode_200()
			throws JsonParseException, JsonMappingException, IOException, InterruptedException, URISyntaxException {
		GenericResponse response = get(BASE_URL + API_PATH + "/" + review.getId());
		HashMap result = objMapper.readValue(response.getResult().toString(), HashMap.class);
		String id = (String) result.get("id");
		assertEquals(response.getCode().intValue(), 200);
		assertNotNull(result);
		assertNotNull(id);
		review.setId(id);
	}

//	@Test
	public void shouldDeleteReviewAndReturnStatusCode_200() {

	}

}
