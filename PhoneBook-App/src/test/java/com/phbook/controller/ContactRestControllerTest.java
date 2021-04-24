package com.phbook.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collection;
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
import com.phbook.service.ContactService;

@WebMvcTest(value = ContactRestController.class)
public class ContactRestControllerTest {
	@MockBean
	private ContactService contactService;

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
	public void test_getAllContacts_01() throws Exception {
		when(contactService.getAllContacts()).thenReturn(Collections.EMPTY_LIST);
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.get("/api/contact");
		MvcResult mvcResult = mockMvc.perform(reqBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(200, status);
	}

	@Test
	public void test_getAllContacts_02() throws Exception {
		when(contactService.getAllContacts()).thenThrow(RuntimeException.class);
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.get("/api/contact");
		MvcResult mvcResult = mockMvc.perform(reqBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(200, status);
	}
}
