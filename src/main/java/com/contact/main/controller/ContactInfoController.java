package com.contact.main.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.contact.main.constants.AppConstants;
import com.contact.main.model.Contact;
import com.contact.main.service.ContactService;

@Controller
public class ContactInfoController {

	private ContactService contactService;

	@Autowired
	public ContactInfoController(ContactService contactService) {
		super();
		this.contactService = contactService;
	}

	@GetMapping(value = { "/loadForm", "/" })
	public String loadForm(Model model) {
		Contact contactObject = new Contact();
		model.addAttribute(AppConstants.CONTACT, contactObject);
		return AppConstants.CONTACT;
	}

	@PostMapping("/saveContact")
	public String handlSaveBtnClick(Contact contact, Model model) {

		Boolean contactExist = contactService.isContactExist(contact);
		if (contactExist) {
			model.addAttribute("error", "Duplicate Contact Found");
		} else {
			boolean isSaved = contactService.saveContact(contact);

			if (isSaved) {
				model.addAttribute("saved", "Contact Saved Successfully...!!!");
			} else {
				model.addAttribute("error", "Contact Not Saved...!!!");
			}
		}
		return AppConstants.CONTACT;
	}

	@GetMapping("/viewContacts")
	public ModelAndView handleViewAllContactsClick(HttpServletRequest req) {
		Integer pageSize = 3;
		Integer pageNo = 1;

		String reqParameter = req.getParameter("pno");
		if (reqParameter != null && !"".equals(reqParameter)) {
			pageNo = Integer.parseInt(reqParameter);
		}
		Page<Contact> page = contactService.getAllContactsPagination(pageNo - 1, pageSize);

		int totalPages = page.getTotalPages();
		List<Contact> allContacts = page.getContent();

		ModelAndView mav = new ModelAndView();

		mav.addObject("contacts", allContacts);
		mav.addObject("totalPages", totalPages);
		mav.addObject("currPno", pageNo);

		mav.setViewName("viewContacts");
		return mav;
	}

}
