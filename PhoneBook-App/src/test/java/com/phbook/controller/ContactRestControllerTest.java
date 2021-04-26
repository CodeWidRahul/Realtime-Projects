package com.phbook.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phbook.entity.Contact;
import com.phbook.props.ApplicationProperties;
import com.phbook.service.ContactService;

@WebMvcTest(value = ContactRestController.class)
public class ContactRestControllerTest {
	@MockBean
	private ContactService contactService;

	@MockBean
	private ApplicationProperties appProps;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testSaveContact_01() throws Exception {
		when(contactService.saveContact(Mockito.any())).thenReturn(true);

		Contact contact = new Contact(101, "Rahul", "rahul@gmail.com", "342332332");

		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		try {
			json = mapper.writeValueAsString(contact);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.post("/api/contact")
				.contentType("application/json").content(json);

		MvcResult mvcResult = mockMvc.perform(reqBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(200, status);
	}

	@Test
	public void testSaveContact_02() throws Exception {
		when(contactService.saveContact(Mockito.any())).thenReturn(false);

		Contact contact = new Contact(101, "Rahul", "rahul@gmail.com", "342332332");

		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		try {
			json = mapper.writeValueAsString(contact);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.post("/api/contact")
				.contentType("application/json").content(json);

		MvcResult mvcResult = mockMvc.perform(reqBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(500, status);
	}

	@Test
	public void testSaveContact_03() throws Exception {
		when(contactService.saveContact(Mockito.any())).thenThrow(RuntimeException.class);

		Contact contact = new Contact(101, "Rahul", "rahul@gmail.com", "342332332");

		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		try {
			json = mapper.writeValueAsString(contact);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.post("/api/contact")
				.contentType("application/json").content(json);

		MvcResult mvcResult = mockMvc.perform(reqBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(500, status);
	}

	@Test
	public void testGetAllContacts_01() throws Exception {
		when(contactService.getAllContacts()).thenReturn(Collections.emptyList());
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.get("/api/contact");
		MvcResult mvcResult = mockMvc.perform(reqBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(200, status);
	}

	/*
	 * @Test public void testGetAllContacts_02() throws Exception {
	 * when(contactService.getAllContacts()).thenReturn(Collections.emptyList());
	 * MockHttpServletRequestBuilder reqBuilder =
	 * MockMvcRequestBuilders.get("/api/contact"); MvcResult mvcResult =
	 * mockMvc.perform(reqBuilder).andReturn(); MockHttpServletResponse response =
	 * mvcResult.getResponse(); int status = response.getStatus(); assertEquals(200,
	 * status); }
	 */

	@Test
	public void testGetAllContacts_03() throws Exception {
		when(contactService.getAllContacts()).thenThrow(RuntimeException.class);
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.get("/api/contact");
		MvcResult mvcResult = mockMvc.perform(reqBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(200, status);
	}

	@Test
	public void testGetContactById_01() throws Exception {
		Contact contact = new Contact(101, "Rahul", "rahul@gmail.com", "342332332");
		when(contactService.getContactById(Mockito.anyInt())).thenReturn(contact);
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.get("/api/contact/" + Mockito.anyInt());
		MvcResult mvcResult = mockMvc.perform(reqBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(200, status);
	}

	@Test
	public void testGetContactById_02() throws Exception {
		when(contactService.getContactById(Mockito.anyInt())).thenThrow(RuntimeException.class);
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.get("/api/contact/" + Mockito.anyInt());
		MvcResult mvcResult = mockMvc.perform(reqBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(500, status);
	}

	@Test
	public void testGetContactById_03() throws Exception {
		when(contactService.getContactById(Mockito.anyInt())).thenReturn(null);
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.get("/api/contact/" + Mockito.anyInt());
		MvcResult mvcResult = mockMvc.perform(reqBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(500, status);
	}

	@Test
	public void testDeleteContactById_01() throws Exception {
		when(contactService.deleteContactById(Mockito.anyInt())).thenReturn(true);
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.delete("/api/contact/1");
		mockMvc.perform(reqBuilder).andExpect(status().isOk());
	}

	@Test
	public void testDeleteContactById_02() throws Exception {
		when(contactService.deleteContactById(Mockito.anyInt())).thenReturn(false);
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.delete("/api/contact/1");
		mockMvc.perform(reqBuilder).andExpect(status().isInternalServerError());
	}

	@Test
	public void testDeleteContactById_03() throws Exception {
		when(contactService.deleteContactById(Mockito.anyInt())).thenThrow(RuntimeException.class);
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.delete("/api/contact/1");
		mockMvc.perform(reqBuilder).andExpect(status().isInternalServerError());
	}
}
