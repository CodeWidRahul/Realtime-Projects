package com.usermgmt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "COUNTRY_MASTER")
public class Country {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "COUNTRY_ID")
	private Integer countryId;

	@Column(name = "COUNTRY_CODE")
	private String countryCode;

	@Column(name = "COUNTRY_NAME")
	private String countryName;
}
