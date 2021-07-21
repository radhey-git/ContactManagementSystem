package com.contact.main.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.contact.main.model.Contact;

public interface ContactService {

	public boolean saveContact(Contact contact);
	
	public Page<Contact> getAllContactsPagination(Integer pageNo,Integer pageSize); 

	public List<Contact> getAllContacts();

	public Contact getContactById(Integer contactId);

	public boolean deleteContactById(Integer contactId);
	
	public Boolean isContactExist(Contact contact);
}
