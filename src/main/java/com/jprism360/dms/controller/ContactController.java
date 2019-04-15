package com.jprism360.dms.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jprism360.dms.domain.Contact;
import com.jprism360.dms.domain.ContactDTO;
import com.jprism360.dms.domain.ContactGroup;
import com.jprism360.dms.service.ContactRepository;
import com.jprism360.dms.service.GroupRepository;

@RestController
public class ContactController {

	private final Logger logger = LoggerFactory.getLogger(ContactController.class);

	@Autowired
	private ContactRepository contactService;

	@Autowired
	private GroupRepository groupService;

	@SuppressWarnings("finally")
	@RequestMapping(value = "/api/contacts/list", produces = "application/json", method = RequestMethod.GET)
	public ResponseEntity<?> getAllContacts() {

		List<Contact> response = new ArrayList<Contact>();
		ResponseEntity<?> entity = null;

		try {

			logger.info("Returning all contacts");

			response.addAll(contactService.findAll());

			entity = ResponseEntity.ok(response);

		} catch (Exception e) {
			e.printStackTrace();
			entity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} finally {

			return entity;
		}
	}

	@SuppressWarnings("finally")
	@RequestMapping(value = "/api/contacts/firstname/{name}", produces = "application/json", method = RequestMethod.GET)
	public ResponseEntity<?> findByFirstName(@PathVariable("name") String firstName) {

		List<Contact> response = new ArrayList<Contact>();
		ResponseEntity<?> entity = null;

		try {

			logger.info("Returning by Firstname : " + firstName);

			response.addAll(contactService.findByFirstName(firstName));

			entity = ResponseEntity.ok(response);

		} catch (Exception e) {
			e.printStackTrace();
			entity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} finally {

			return entity;
		}
	}

	@SuppressWarnings("finally")
	@RequestMapping(value = "/api/contacts/lastname/{name}", produces = "application/json", method = RequestMethod.GET)
	public ResponseEntity<?> findByLastName(@PathVariable("name") String lastName) {

		List<Contact> response = new ArrayList<Contact>();
		ResponseEntity<?> entity = null;

		try {

			logger.info("Returning by LastName : " + lastName);

			response.addAll(contactService.findByLastName(lastName));

			entity = ResponseEntity.ok(response);

		} catch (Exception e) {
			e.printStackTrace();
			entity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} finally {

			return entity;
		}
	}

	@SuppressWarnings("finally")
	@RequestMapping(value = "/api/contacts/address/{address}", produces = "application/json", method = RequestMethod.GET)
	public ResponseEntity<?> findByAddress(@PathVariable("address") String address) {

		// assume that address is not unique
		List<Contact> response = new ArrayList<Contact>();
		ResponseEntity<?> entity = null;

		try {

			logger.info("Returning by Address : " + address);

			response.addAll(contactService.findByAddress(address));

			entity = ResponseEntity.ok(response);

		} catch (Exception e) {
			e.printStackTrace();
			entity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} finally {

			return entity;
		}
	}

	@SuppressWarnings("finally")
	@RequestMapping(value = "/api/contacts/hometel/{hometel}", produces = "application/json", method = RequestMethod.GET)
	public ResponseEntity<?> findByHomeTelephone(@PathVariable("hometel") String hometel) {

		// assume hometel is not unique
		List<Contact> response = new ArrayList<Contact>();
		ResponseEntity<?> entity = null;

		try {

			logger.info("Returning by Home Telephone : " + hometel);

			response.addAll(contactService.findByHomeTel(hometel));

			entity = ResponseEntity.ok(response);

		} catch (Exception e) {
			e.printStackTrace();
			entity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} finally {

			return entity;
		}
	}

	@SuppressWarnings("finally")
	@RequestMapping(value = "/api/contacts/mobiletel/{mobiletel}", produces = "application/json", method = RequestMethod.GET)
	public ResponseEntity<?> findByMobileTelephone(@PathVariable("mobiletel") String mobiletel) {

		// assume mobile phone is not unique
		List<Contact> response = new ArrayList<Contact>();
		ResponseEntity<?> entity = null;

		try {

			logger.info("Returning by Mobile Telephone : " + mobiletel);

			response.addAll(contactService.findByMobileTel(mobiletel));

			entity = ResponseEntity.ok(response);

		} catch (Exception e) {
			e.printStackTrace();
			entity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} finally {

			return entity;
		}
	}

	@SuppressWarnings("finally")
	@RequestMapping(value = "/api/contacts/worktel/{worktel}", produces = "application/json", method = RequestMethod.GET)
	public ResponseEntity<?> findByWorkTelephone(@PathVariable("worktel") String worktel) {

		// assume work telephone is not unique
		List<Contact> response = new ArrayList<Contact>();
		ResponseEntity<?> entity = null;

		try {

			logger.info("Returning by Work Telephone : " + worktel);

			response.addAll(contactService.findByHomeTel(worktel));

			entity = ResponseEntity.ok(response);

		} catch (Exception e) {
			e.printStackTrace();
			entity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} finally {

			return entity;
		}
	}

	@SuppressWarnings("finally")
	@RequestMapping(value = "/api/contacts/group/{gid}", produces = "application/json", method = RequestMethod.GET)
	public ResponseEntity<?> findAllContractsByGroup(@PathVariable("gid") Long gid) {

		List<Contact> response = new ArrayList<Contact>();
		ResponseEntity<?> entity = null;

		try {

			logger.info("Returning All contracts by Group Id : " + gid);

			Optional<ContactGroup> group = groupService.findById(gid);

			if (group.isPresent()) {
				response.addAll(group.get().getContacts());
			}

			entity = ResponseEntity.ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			entity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} finally {

			return entity;
		}
	}

	@SuppressWarnings("finally")
	@RequestMapping(value = "/api/contact/{id}", produces = "application/json", method = RequestMethod.PUT)
	public ResponseEntity<?> saveOrUpdateContact(@PathVariable Long id, @Valid @RequestBody ContactDTO contactRequest) {

		ResponseEntity<?> entity = null;

		try {

			Optional<Contact> contactOptional = contactService.findById(id);

			Contact contact = null;
			if (contactOptional.isPresent()) {
				contact = contactOptional.get();
			} else {
				contact = new Contact();
				contact.setId(id);
				
				contact.setGroup(contactRequest.getGroup());
			}

			contact.setFirstName(contactRequest.getFirstName());
			contact.setLastName(contactRequest.getLastName());
			contact.setAddress(contactRequest.getAddress());
			contact.setHomeTel(contactRequest.getHomeTel());
			contact.setMobileTel(contactRequest.getMobileTel());
			contact.setWorkTel(contactRequest.getWorkTel());
			
			contactService.save(contact);

			entity = ResponseEntity.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			entity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} finally {

			return entity;
		}

	}

	@RequestMapping(value = "/api/contact/{id}", produces = "application/json", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteContact(@PathVariable Long id) {

		return contactService.findById(id).map(contact -> {
			contactService.delete(contact);
			return ResponseEntity.ok().build();
		}).orElse(new ResponseEntity<Object>(HttpStatus.NOT_FOUND));
	}

}
