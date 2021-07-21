package com.contact.main.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.contact.main.model.Contact;
import com.contact.main.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {

	private ContactRepository contactRepo;

	@Autowired
	private ContactServiceImpl(ContactRepository contactRepo) {
		super();
		this.contactRepo = contactRepo;
	}

	@Override
	public boolean saveContact(Contact contact) {

		contact.setActiveSW("Y");

		Contact saveEntity = contactRepo.save(contact);

		if (saveEntity != null && saveEntity.getContactId() != null) {
			return true;
		}
		return false;
	}

	@Override
	public List<Contact> getAllContacts() {
		Contact conObj = new Contact();
		conObj.setActiveSW("Y");
		Example<Contact> example = Example.of(conObj);
		return contactRepo.findAll(example);
	}

	@Override
	public Page<Contact> getAllContactsPagination(Integer pageNo, Integer pageSize) // Here, Define pageNo And pageSize
	{
		Contact contactFilter = new Contact();
		contactFilter.setActiveSW("Y");
		Example<Contact> example = Example.of(contactFilter);
		PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
		return contactRepo.findAll(example, pageRequest);
	}

	@Override
	public Contact getContactById(Integer contactId) {

		Optional<Contact> findById = contactRepo.findById(contactId);
		if (findById.isPresent()) {
			return findById.get();
		}
		return null;
	}

	@Override
	public boolean deleteContactById(Integer contactId) {
		Optional<Contact> contact = contactRepo.findById(contactId);// get Object By ID
		if (contact.isPresent()) {
			Contact conObj = contact.get();
			conObj.setActiveSW("N");
			contactRepo.save(conObj);
			return true;
		}
		return false;
	}

	@Override
	public Boolean isContactExist(Contact contact) {
		
		Example<Contact> example = Example.of(contact);
		List<Contact> contacts = contactRepo.findAll(example);
		
		if(contacts.isEmpty()) {
			return true;
		}
		return false;
	}

}
