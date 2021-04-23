package com.phbook.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CONTACT_DTLS")
@Setter
@Getter
@XmlRootElement
public class Contact {
	@Id
	@GeneratedValue
	@Column(name = "CONTACT_ID")
	private Integer contactId;

	@Column(name = "CONTACT_NAME")
	private String contactName;

	@Column(name = "CONTACT_EMAIL")
	private String contactEmail;

	@Column(name = "CONTACT_NUMBER")
	private String contactNumber;

	
}
