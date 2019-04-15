package com.jprism360.dms;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.jprism360.dms.domain.Contact;
import com.jprism360.dms.domain.ContactDTO;
import com.jprism360.dms.domain.ContactGroup;

public class ContactControllerTest extends AbstractBaseTest {

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void getContacts() throws Exception {
		String restPath = "/api/contacts/list";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(restPath).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		// test the status
		assertEquals(200, mvcResult.getResponse().getStatus());

		String content = mvcResult.getResponse().getContentAsString();
		Contact[] contacts = super.objectFromJson(content, Contact[].class);
		assertTrue(contacts.length > 0);
	}

	@Test
	public void addNewContact() throws Exception {
		String restPath = "/api/contact/7";
		ContactDTO contact = new ContactDTO();
		contact.setFirstName("James");
		contact.setLastName("King");
		contact.setAddress("123 Summerville Street, Hamilton");
		contact.setHomeTel("9054324455");
		
		ContactGroup group = new ContactGroup();
		group.setId(1L);
		
		contact.setGroup(group);
	
		String inputJson = objectToJson(contact);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(restPath)
								.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
								.andReturn();

		
		assertEquals(200, mvcResult.getResponse().getStatus());
		
	}
	
	@Test
	public void deleteContact() throws Exception {
		String restPath = "/api/contact/2";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(restPath).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		// test the status
		assertEquals(200, mvcResult.getResponse().getStatus());
		
	}

}
