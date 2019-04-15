package com.jprism360.dms.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jprism360.dms.domain.Contact;
import com.jprism360.dms.domain.ContactGroup;

public interface ContactRepository extends JpaRepository<Contact, Long> {
	
	List<Contact> findByFirstName(String firstName);
	List<Contact> findByLastName(String lastName);
	List<Contact> findByAddress(String address);
	List<Contact> findByHomeTel(String homeTel);
	List<Contact> findByWorkTel(String workTel);
	List<Contact> findByMobileTel(String mobileTel);
	
	
//	@Query("SELECT new Group(d.id,d.name) FROM Group d INNER JOIN Contract")
//	List<ContractGroup> fetchEmpDeptDataInnerJoin();
	

}
