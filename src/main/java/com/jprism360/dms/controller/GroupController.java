package com.jprism360.dms.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jprism360.dms.domain.Contact;
import com.jprism360.dms.domain.ContactGroup;
import com.jprism360.dms.service.GroupRepository;

@RestController
public class GroupController {
	
	private final Logger logger = LoggerFactory.getLogger(GroupController.class);
	
	@Autowired
	private GroupRepository groupService;
	
	
	
	@SuppressWarnings("finally")
	@RequestMapping(value = "/api/groups/name/{name}", produces = "application/json", method= RequestMethod.GET)
	public ResponseEntity<?> findByName(@PathVariable("name") String name) {
		
		
		List<ContactGroup> response = new ArrayList<ContactGroup>();
		ResponseEntity<?> entity = null;
		
		
		try {

			logger.info("Returning by Name : " + name);
			
			response.addAll(groupService.findByName(name));
			
			entity = ResponseEntity.ok(response);
			
		}
		catch(Exception e) {
			e.printStackTrace();	
			entity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		finally {
			
			return entity;
		}
	}
	

	

}
