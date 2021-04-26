package com.phbook.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phbook.constants.ApplicationConstants;
import com.phbook.entity.Contact;
import com.phbook.exception.NoDataFoundException;
import com.phbook.props.ApplicationProperties;
import com.phbook.service.ContactService;

@RestController
@RequestMapping("/api/contact")
public class ContactRestController {

	private Logger logger = LoggerFactory.getLogger(ContactRestController.class);

	private ContactService contactService;

	private ApplicationProperties appProps;

	public ContactRestController(ContactService contactService, ApplicationProperties appProps) {
		this.contactService = contactService;
		this.appProps = appProps;
	}

	@PostMapping
	public ResponseEntity<String> saveContact(@RequestBody Contact contact) {
		logger.debug("** saveConatct() - Execution started. **");
		try {
			boolean isSaved = contactService.saveContact(contact);
			if (isSaved) {
				logger.info("** saveConatct() - Contact saved. **");
				String successMsg = appProps.getMessages().get(ApplicationConstants.SAVE_CONTACT_SUCCESS);
				return new ResponseEntity<>(successMsg, HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error("** Exception occurred :- **" + e);
		}
		logger.info("** saveConatct() - Contact not saved. **");
		logger.debug("** saveConatct() - Execution ended. **");
		String failedMsg = appProps.getMessages().get(ApplicationConstants.SAVE_CONTACT_FAIL);
		return new ResponseEntity<>(failedMsg, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping
	public ResponseEntity<List<Contact>> getAllContacts() {
		logger.debug("** getAllContacts() - Execution started. **");
		List<Contact> allContacts = null;
		try {
			allContacts = contactService.getAllContacts();
			if (allContacts.isEmpty())
				logger.info("** getAllContacts() - Record not available ! **");
		} catch (Exception e) {
			logger.error("** Exception occurred :- **" + e);
		}
		logger.debug("** getAllContacts() - Execution ended. **");
		return new ResponseEntity<>(allContacts, HttpStatus.OK);
	}

	@GetMapping("/{contactId}")
	public ResponseEntity<Object> getContactById(@PathVariable Integer contactId) {
		logger.debug("** getContactById() - Execution started. **");
		Contact contact = null;
		ResponseEntity<Object> responseEntity;
		try {
			contact = contactService.getContactById(contactId);
			if (contact == null) {
				logger.info("** getContactById() - No contact found ! **");
				String notFoundMsg = appProps.getMessages().get(ApplicationConstants.NO_CONTACT_FOUND);
				throw new NoDataFoundException(notFoundMsg);
			} else
				responseEntity = new ResponseEntity<>(contact, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("** Exception occurred :- **" + e);
			responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		logger.debug("** getContactById() - Execution ended. **");
		return responseEntity;
	}

	@DeleteMapping("/{contactId}")
	public ResponseEntity<String> deleteContactById(@PathVariable Integer contactId) {
		logger.debug("** deleteContactById() - Execution started. **");
		ResponseEntity<String> responseEntity = null;
		String failMsg = appProps.getMessages().get(ApplicationConstants.DELETE_CONTACT_FAIL);
		try {
			boolean isDeleted = contactService.deleteContactById(contactId);
			if (isDeleted) {
				logger.info("** deleteContactById() - contact deleted. **");
				String successMsg = appProps.getMessages().get(ApplicationConstants.DELETE_CONTACT_SUCCESS);
				responseEntity = new ResponseEntity<>(successMsg, HttpStatus.OK);
			} else
				responseEntity = new ResponseEntity<>(failMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			logger.error("** Exception occurred :- **" + e);
			responseEntity = new ResponseEntity<>(failMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		logger.debug("** deleteContactById() - Execution ended. **");
		return responseEntity;
	}
}
