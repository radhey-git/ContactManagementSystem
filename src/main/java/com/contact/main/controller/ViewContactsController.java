package com.contact.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.contact.main.model.Contact;
import com.contact.main.service.ContactService;

@Controller
public class ViewContactsController {
	private ContactService contactService;

	public ViewContactsController(ContactService contactService) {
		super();
		this.contactService = contactService;
	}

	@GetMapping("/editContact")
	public ModelAndView handleEditClickLink(@RequestParam("cid") Integer contactId) {
		ModelAndView mav = new ModelAndView();

		Contact conObj = contactService.getContactById(contactId);
		mav.addObject("contact", conObj);
		mav.setViewName("contact");
		
		return mav;
	}
	
	@GetMapping("/deleteContact")
	public String handleDeleteLinkClick(@RequestParam("cid") Integer contactId)
	{
		contactService.deleteContactById(contactId);
		
		return "redirect:viewContacts";
	}
}
