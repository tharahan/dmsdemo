package com.jprism360.dms.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jprism360.dms.domain.Contact;
import com.jprism360.dms.domain.ContactGroup;

public interface GroupRepository extends JpaRepository<ContactGroup, Long> {
	List<ContactGroup> findByName(String groupName);
	
	// @Query("SELECT Contract FROM Group LEFT JOIN Contract ON Group.id = Contract.groupId WHERE Group.id = 1")
//	@Query("SELECT Contract FROM Group g JOIN Contract")
//	List<Contract> findContractsByGroup();

}
