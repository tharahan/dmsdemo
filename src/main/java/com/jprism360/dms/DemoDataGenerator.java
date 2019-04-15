package com.jprism360.dms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.jprism360.dms.domain.Contact;
import com.jprism360.dms.domain.ContactGroup;
import com.jprism360.dms.service.ContactRepository;
import com.jprism360.dms.service.GroupRepository;

@Component
public class DemoDataGenerator {

	@Autowired
	private ContactRepository contactService;
	
	@Autowired
	private GroupRepository groupService;

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
    	
    	ContactGroup group = new ContactGroup(1L, "Development");
    	groupService.save(group);
    	
    	Contact contact = new Contact(1L, "Peter", "Parker", "1 Main Street, Toronto", "4163434322", "6474326789","6474326789");
    	contact.setGroup(group);
    	contactService.save(contact);
    	
    	contact = new Contact(2L, "Rob", "Hawks", "445 Yonge Street, North York", "6473786556", "6473786556","6479453345");
    	contact.setGroup(group);
    	contactService.save(contact);
    	
    	contact = new Contact(3L, "John", "Dao", "44 Four Drive, North York", "4165256989", "4169895623","4169895623");
    	contact.setGroup(group);
    	contactService.save(contact);
    	
    	group = new ContactGroup(2L, "Sales");
    	groupService.save(group);
    	
    	contact = new Contact(4L, "Ann", "Amber", "573 Power Road, Oakville", "9056784323", "9056784323","6475658899");
    	contact.setGroup(group);
    	contactService.save(contact);
    	
    	contact = new Contact(5L, "Cindy", "Xu", "101 New Lane, Mississauga", "9056784567", "4164845632","4165524523");
    	contact.setGroup(group);
    	contactService.save(contact);
    	
    	group = new ContactGroup(3L, "HR");
    	groupService.save(group);
    	
    	group = new ContactGroup(4L, "Accounting");
    	groupService.save(group);
    	
    	
    }
}