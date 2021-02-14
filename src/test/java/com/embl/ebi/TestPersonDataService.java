package com.embl.ebi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ebml.ebi.constants.TestConstants;
import com.embl.ebi.beans.Person;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This class is used to test the rest services exposed by EMBL-EBI for the
 * person data service
 * 
 * @author hariprasanth.l
 *
 */
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestPersonDataService {

	/**
	 * Logger object
	 */
	private final static Logger LOG = LoggerFactory.getLogger(TestPersonDataService.class);

	/**
	 * Objectmapper will be used for validation of response
	 */
	private final static ObjectMapper MAPPER = new ObjectMapper();

	/**
	 * Autowired to perform rest call.
	 */
	@Autowired
	private MockMvc mockMvc;

	@Test
	@Order(1)
	public void testStorePersonDataSuccessForOneEntry() throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post(TestConstants.PERSON_API_STORE_URL)
						.contentType(MediaType.APPLICATION_JSON).content(TestConstants.STORE_INPUT_1))
				.andExpect(status().isOk()).andReturn();
		String personData = result.getResponse().getContentAsString();
		JSONArray responseArray = (JSONArray) new JSONObject(personData).get(TestConstants.PERSON_IDS);
		assertTrue(responseArray.length() == 1);
	}

	@Test
	@Order(2)
	public void testStorePersonDataSuccessForMultipleEntry() throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post(TestConstants.PERSON_API_STORE_URL)
						.contentType(MediaType.APPLICATION_JSON).content(TestConstants.STORE_INPUT_2))
				.andExpect(status().isOk()).andReturn();
		String personData = result.getResponse().getContentAsString();
		JSONArray responseArray = (JSONArray) new JSONObject(personData).get(TestConstants.PERSON_IDS);
		assertTrue(responseArray.length() == 3);
	}

	@Test
	@Order(3)
	public void testStorePersonDataFailureForMissingFirstName() throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post(TestConstants.PERSON_API_STORE_URL)
						.contentType(MediaType.APPLICATION_JSON).content(TestConstants.STORE_INPUT_3))
				.andExpect(status().is(500)).andReturn();
		String errorResponse = result.getResponse().getContentAsString();
		assertTrue(errorResponse.contains("firstName is not available"));
	}

	@Test
	@Order(3)
	public void testStorePersonDataFailureForNonAlphabetFirstName() throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post(TestConstants.PERSON_API_STORE_URL)
						.contentType(MediaType.APPLICATION_JSON).content(TestConstants.STORE_INPUT_7))
				.andExpect(status().is(500)).andReturn();
		String errorResponse = result.getResponse().getContentAsString();
		assertTrue(errorResponse.contains("firstName should contain only alphabets"));
	}

	@Test
	@Order(3)
	public void testStorePersonDataFailureForNonAlphabetLastName() throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post(TestConstants.PERSON_API_STORE_URL)
						.contentType(MediaType.APPLICATION_JSON).content(TestConstants.STORE_INPUT_8))
				.andExpect(status().is(500)).andReturn();
		String errorResponse = result.getResponse().getContentAsString();
		assertTrue(errorResponse.contains("lastName should contain only alphabets"));
	}

	@Test
	@Order(3)
	public void testStorePersonDataFailureForNonAlphabetFavouriteColour() throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post(TestConstants.PERSON_API_STORE_URL)
						.contentType(MediaType.APPLICATION_JSON).content(TestConstants.STORE_INPUT_9))
				.andExpect(status().is(500)).andReturn();
		String errorResponse = result.getResponse().getContentAsString();
		assertTrue(errorResponse.contains("favouriteColour should contain only alphabets"));
	}

	@Test
	@Order(3)
	public void testStorePersonDataFailureForLessThan3CharactersOfFirstName() throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post(TestConstants.PERSON_API_STORE_URL)
						.contentType(MediaType.APPLICATION_JSON).content(TestConstants.STORE_INPUT_10))
				.andExpect(status().is(500)).andReturn();
		String errorResponse = result.getResponse().getContentAsString();
		assertTrue(errorResponse.contains(
				"firstName should be greater than or equal to 60 characters and should be less than or equal to 3 characters"));
	}

	@Test
	@Order(3)
	public void testStorePersonDataFailureForGreaterThan60CharactersOfFirstName() throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post(TestConstants.PERSON_API_STORE_URL)
						.contentType(MediaType.APPLICATION_JSON).content(TestConstants.STORE_INPUT_11))
				.andExpect(status().is(500)).andReturn();
		String errorResponse = result.getResponse().getContentAsString();
		assertTrue(errorResponse.contains(
				"firstName should be greater than or equal to 60 characters and should be less than or equal to 3 characters"));
	}

	@Test
	@Order(3)
	public void testStorePersonDataFailureForLessThan3CharactersOfLastName() throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post(TestConstants.PERSON_API_STORE_URL)
						.contentType(MediaType.APPLICATION_JSON).content(TestConstants.STORE_INPUT_12))
				.andExpect(status().is(500)).andReturn();
		String errorResponse = result.getResponse().getContentAsString();
		assertTrue(errorResponse.contains(
				"lastName should be greater than or equal to 60 characters and should be less than or equal to 3 characters"));
	}

	@Test
	@Order(3)
	public void testStorePersonDataFailureForGreaterThan60CharactersOfLastName() throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post(TestConstants.PERSON_API_STORE_URL)
						.contentType(MediaType.APPLICATION_JSON).content(TestConstants.STORE_INPUT_13))
				.andExpect(status().is(500)).andReturn();
		String errorResponse = result.getResponse().getContentAsString();
		assertTrue(errorResponse.contains(
				"lastName should be greater than or equal to 60 characters and should be less than or equal to 3 characters"));
	}

	@Test
	@Order(3)
	public void testStorePersonDataFailureForLessThan3CharactersOfFavouriteColour() throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post(TestConstants.PERSON_API_STORE_URL)
						.contentType(MediaType.APPLICATION_JSON).content(TestConstants.STORE_INPUT_14))
				.andExpect(status().is(500)).andReturn();
		String errorResponse = result.getResponse().getContentAsString();
		assertTrue(errorResponse.contains(
				"favouriteColour should be greater than or equal to 60 characters and should be less than or equal to 3 characters"));
	}

	@Test
	@Order(3)
	public void testStorePersonDataFailureForGreaterThan60CharactersOfFavouriteColour() throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post(TestConstants.PERSON_API_STORE_URL)
						.contentType(MediaType.APPLICATION_JSON).content(TestConstants.STORE_INPUT_15))
				.andExpect(status().is(500)).andReturn();
		String errorResponse = result.getResponse().getContentAsString();
		assertTrue(errorResponse.contains(
				"favouriteColour should be greater than or equal to 60 characters and should be less than or equal to 3 characters"));
	}

	@Test
	@Order(3)
	public void testStorePersonDataFailureForMissingLastName() throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post(TestConstants.PERSON_API_STORE_URL)
						.contentType(MediaType.APPLICATION_JSON).content(TestConstants.STORE_INPUT_4))
				.andExpect(status().is(500)).andReturn();
		String errorResponse = result.getResponse().getContentAsString();
		assertTrue(errorResponse.contains("lastName is not available"));
	}

	@Test
	@Order(3)
	public void testStorePersonDataFailureForMissingFavouriteColour() throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post(TestConstants.PERSON_API_STORE_URL)
						.contentType(MediaType.APPLICATION_JSON).content(TestConstants.STORE_INPUT_5))
				.andExpect(status().is(500)).andReturn();
		String errorResponse = result.getResponse().getContentAsString();
		assertTrue(errorResponse.contains("favouriteColour is not available"));
	}

	@Test
	@Order(3)
	public void testStorePersonDataFailureForAgeAsZero() throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post(TestConstants.PERSON_API_STORE_URL)
						.contentType(MediaType.APPLICATION_JSON).content(TestConstants.STORE_INPUT_6))
				.andExpect(status().is(500)).andReturn();
		String errorResponse = result.getResponse().getContentAsString();
		assertTrue(errorResponse.contains("age should be greater than or equal to 1"));
	}

	@Test
	@Order(3)
	public void testGetPersonDataByPersonIdSuccess() throws Exception {
		// Write and Fetch the same data for verification
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post(TestConstants.PERSON_API_STORE_URL)
						.contentType(MediaType.APPLICATION_JSON).content(TestConstants.STORE_INPUT_2))
				.andExpect(status().isOk()).andReturn();
		String personData = result.getResponse().getContentAsString();
		JSONArray responseArray = (JSONArray) new JSONObject(personData).get(TestConstants.PERSON_IDS);
		String storedPersonId = responseArray.getString(0);
		MvcResult getByIdResponse = mockMvc
				.perform(MockMvcRequestBuilders.get(TestConstants.PERSON_API_GET_URL + "/" + storedPersonId))
				.andExpect(status().isOk()).andReturn();
		String storedPersonData = getByIdResponse.getResponse().getContentAsString();
		Person person = MAPPER.readValue(storedPersonData, Person.class);
		assertEquals(String.valueOf(person.getPersonId()), storedPersonId);
	}

	@Test
	@Order(3)
	public void testGetPersonDataByPersonIdFailureForInvalidId() throws Exception {
		long invalidId = -1;
		MvcResult getByIdResponse = mockMvc
				.perform(MockMvcRequestBuilders.get(TestConstants.PERSON_API_GET_URL + "/" + invalidId))
				.andExpect(status().is(500)).andReturn();
		String storedPersonData = getByIdResponse.getResponse().getContentAsString();
		assertTrue(storedPersonData.contains("Person Data is not available for the id : -1"));
	}

	@Test
	@Order(3)
	public void testGetPersonDataByPersonIdFailureForDefaultLongValue() throws Exception {
		long invalidId = 0;
		MvcResult getByIdResponse = mockMvc
				.perform(MockMvcRequestBuilders.get(TestConstants.PERSON_API_GET_URL + "/" + invalidId))
				.andExpect(status().is(500)).andReturn();
		String storedPersonData = getByIdResponse.getResponse().getContentAsString();
		assertTrue(storedPersonData.contains("PersonId is not available"));
	}

	@Test
	@Order(3)
	public void testGetAllPersonDataWithDefaultSortAndPaginationSuccess() throws Exception {
		// Write and Fetch all the data for verification
		mockMvc.perform(MockMvcRequestBuilders.post(TestConstants.PERSON_API_STORE_URL)
				.contentType(MediaType.APPLICATION_JSON).content(TestConstants.STORE_INPUT_16))
				.andExpect(status().isOk()).andReturn();
		MvcResult getAllResponse = mockMvc.perform(MockMvcRequestBuilders.get(TestConstants.PERSON_API_GET_URL))
				.andExpect(status().isOk()).andReturn();
		String storedPersonData = getAllResponse.getResponse().getContentAsString();
		int availableTotal = (int) new JSONObject(storedPersonData).get(TestConstants.TOTAL);
		JSONArray dataArray = (JSONArray) new JSONObject(storedPersonData).get(TestConstants.DATA);
		assertTrue(availableTotal > 0);
		assertTrue(dataArray.length() > 0);
	}

	@Test
	@Order(3)
	public void testGetAllPersonDataWithDefaultSortAndPagination0to10Success() throws Exception {
		// Write and Fetch all the data for verification
		mockMvc.perform(MockMvcRequestBuilders.post(TestConstants.PERSON_API_STORE_URL)
				.contentType(MediaType.APPLICATION_JSON).content(TestConstants.STORE_INPUT_16))
				.andExpect(status().isOk()).andReturn();
		MvcResult getAllResponse = mockMvc
				.perform(MockMvcRequestBuilders.get(TestConstants.PERSON_API_GET_URL + "?from=0&size=10"))
				.andExpect(status().isOk()).andReturn();
		String storedPersonData = getAllResponse.getResponse().getContentAsString();
		int availableTotal = (int) new JSONObject(storedPersonData).get(TestConstants.TOTAL);
		JSONArray dataArray = (JSONArray) new JSONObject(storedPersonData).get(TestConstants.DATA);
		assertTrue(availableTotal == 10);
		assertTrue(dataArray.length() == 10);
	}

	@Test
	@Order(3)
	public void testGetAllPersonDataWithSortByFirstNameSuccess() throws Exception {
		// Write and Fetch all the data for verification
		mockMvc.perform(MockMvcRequestBuilders.post(TestConstants.PERSON_API_STORE_URL)
				.contentType(MediaType.APPLICATION_JSON).content(TestConstants.STORE_INPUT_16))
				.andExpect(status().isOk()).andReturn();
		MvcResult getAllResponse = mockMvc
				.perform(
						MockMvcRequestBuilders.get(TestConstants.PERSON_API_GET_URL + "?sort=firstName&from=0&size=10"))
				.andExpect(status().isOk()).andReturn();
		String storedPersonData = getAllResponse.getResponse().getContentAsString();
		int availableTotal = (int) new JSONObject(storedPersonData).get(TestConstants.TOTAL);
		JSONArray dataArray = (JSONArray) new JSONObject(storedPersonData).get(TestConstants.DATA);
		assertTrue(availableTotal == 10);
		// Fetch first entry to check the sort
		Person person = MAPPER.readValue(dataArray.get(0).toString(), Person.class);
		assertEquals(person.getFirstName(), "Andrew");
	}

	@Test
	@Order(3)
	public void testGetAllPersonDataWithSortByLastNameSuccess() throws Exception {
		// Write and Fetch all the data for verification
		mockMvc.perform(MockMvcRequestBuilders.post(TestConstants.PERSON_API_STORE_URL)
				.contentType(MediaType.APPLICATION_JSON).content(TestConstants.STORE_INPUT_16))
				.andExpect(status().isOk()).andReturn();
		MvcResult getAllResponse = mockMvc
				.perform(MockMvcRequestBuilders.get(TestConstants.PERSON_API_GET_URL + "?sort=lastName&from=0&size=10"))
				.andExpect(status().isOk()).andReturn();
		String storedPersonData = getAllResponse.getResponse().getContentAsString();
		int availableTotal = (int) new JSONObject(storedPersonData).get(TestConstants.TOTAL);
		JSONArray dataArray = (JSONArray) new JSONObject(storedPersonData).get(TestConstants.DATA);
		assertTrue(availableTotal == 10);
		// Fetch first entry to check the sort
		Person person = MAPPER.readValue(dataArray.get(0).toString(), Person.class);
		assertEquals(person.getLastName(), "Angela");
	}

	@Test
	@Order(3)
	public void testGetAllPersonDataWithSortByFavouriteColourSuccess() throws Exception {
		// Write and Fetch all the data for verification
		mockMvc.perform(MockMvcRequestBuilders.post(TestConstants.PERSON_API_STORE_URL)
				.contentType(MediaType.APPLICATION_JSON).content(TestConstants.STORE_INPUT_16))
				.andExpect(status().isOk()).andReturn();
		MvcResult getAllResponse = mockMvc
				.perform(MockMvcRequestBuilders.get(TestConstants.PERSON_API_GET_URL + "?sort=favouriteColour"))
				.andExpect(status().isOk()).andReturn();
		String storedPersonData = getAllResponse.getResponse().getContentAsString();
		JSONArray dataArray = (JSONArray) new JSONObject(storedPersonData).get(TestConstants.DATA);
		// Fetch first entry to check the sort
		Person person = MAPPER.readValue(dataArray.get(0).toString(), Person.class);
		assertEquals(person.getFavouriteColour(), "Black");
	}

	@Test
	@Order(3)
	public void testGetAllPersonDataWithSortByAgeSuccess() throws Exception {
		// Write and Fetch all the data for verification
		mockMvc.perform(MockMvcRequestBuilders.post(TestConstants.PERSON_API_STORE_URL)
				.contentType(MediaType.APPLICATION_JSON).content(TestConstants.STORE_INPUT_16))
				.andExpect(status().isOk()).andReturn();
		MvcResult getAllResponse = mockMvc
				.perform(MockMvcRequestBuilders.get(TestConstants.PERSON_API_GET_URL + "?sort=age&from=0&size=10"))
				.andExpect(status().isOk()).andReturn();
		String storedPersonData = getAllResponse.getResponse().getContentAsString();
		int availableTotal = (int) new JSONObject(storedPersonData).get(TestConstants.TOTAL);
		JSONArray dataArray = (JSONArray) new JSONObject(storedPersonData).get(TestConstants.DATA);
		assertTrue(availableTotal == 10);
		// Fetch first entry to check the sort
		Person person = MAPPER.readValue(dataArray.get(0).toString(), Person.class);
		assertEquals(person.getAge(), 1);
	}

	@Test
	@Order(3)
	public void testGetAllPersonDataWithWrongSortKeyFailure() throws Exception {
		// Write and Fetch all the data for verification
		mockMvc.perform(MockMvcRequestBuilders.post(TestConstants.PERSON_API_STORE_URL)
				.contentType(MediaType.APPLICATION_JSON).content(TestConstants.STORE_INPUT_16))
				.andExpect(status().isOk()).andReturn();
		MvcResult getAllResponse = mockMvc
				.perform(MockMvcRequestBuilders.get(TestConstants.PERSON_API_GET_URL + "?sort=test&from=0&size=10"))
				.andExpect(status().is(500)).andReturn();
		String storedPersonData = getAllResponse.getResponse().getContentAsString();
		assertTrue(storedPersonData.contains("Sort field is not accepted : test"));
	}

	@Test
	@Order(3)
	public void testdeleteByIdSuccess() throws Exception {
		// Write, Delete and Fetch the same data for verification
		// write
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post(TestConstants.PERSON_API_STORE_URL)
						.contentType(MediaType.APPLICATION_JSON).content(TestConstants.STORE_INPUT_2))
				.andExpect(status().isOk()).andReturn();
		String personData = result.getResponse().getContentAsString();
		JSONArray responseArray = (JSONArray) new JSONObject(personData).get(TestConstants.PERSON_IDS);
		String storedPersonId = responseArray.getString(0);
		// delete
		MvcResult deleteResponse = mockMvc
				.perform(MockMvcRequestBuilders.delete(TestConstants.PERSON_API_DELETE_URL + "/" + storedPersonId))
				.andExpect(status().isOk()).andReturn();
		String deletedResponse = deleteResponse.getResponse().getContentAsString();
		assertTrue(deletedResponse.contains("Person data is deleted successfully from the data store, personId : "+storedPersonId));
		// fetch
		MvcResult getByIdResponse = mockMvc
				.perform(MockMvcRequestBuilders.get(TestConstants.PERSON_API_GET_URL + "/" + storedPersonId))
				.andExpect(status().is(500)).andReturn();
		String storedPersonData = getByIdResponse.getResponse().getContentAsString();
		assertTrue(storedPersonData.contains("Person Data is not available for the id : " + storedPersonId));
	}
	
	@Test
	@Order(4)
	public void testdeleteAllPersonDataSuccess() throws Exception {
		// Write, Delete all and Fetch all the data for verification
		// write
		mockMvc.perform(MockMvcRequestBuilders.post(TestConstants.PERSON_API_STORE_URL)
						.contentType(MediaType.APPLICATION_JSON).content(TestConstants.STORE_INPUT_16))
				.andExpect(status().isOk()).andReturn();
		// Delete All
		MvcResult deleteAllResponse = mockMvc
				.perform(MockMvcRequestBuilders.delete(TestConstants.PERSON_API_DELETE_ALL_URL))
				.andExpect(status().isOk()).andReturn();
		String deletedPersonDataResponse = deleteAllResponse.getResponse().getContentAsString();
		assertTrue(deletedPersonDataResponse.contains("All the Person data's are deleted successfully from the data store"));
		// Verify - GET ALL
		MvcResult getAllResponse = mockMvc.perform(MockMvcRequestBuilders.get(TestConstants.PERSON_API_GET_URL))
				.andExpect(status().isOk()).andReturn();
		String storedPersonData = getAllResponse.getResponse().getContentAsString();
		int availableTotal = (int) new JSONObject(storedPersonData).get(TestConstants.TOTAL);
		assertTrue(availableTotal == 0);
	}

}
