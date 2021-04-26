package com.usermgmt.entity;

import java.util.Date;

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
@Table(name = "USER_ACCOUNTS")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID")
	private Integer userId;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "DOB")
	private Date dob;

	@Column(name = "GENDER")
	private String gender;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "PHONE_NO")
	private Long phoneNo;

	@Column(name = "ACC_STATUS")
	private String accountStatus;

	@Column(name = "CITY_ID")
	private Integer cityId;

	@Column(name = "STATE_ID")
	private Integer stateId;

	@Column(name = "COUNTRY_ID")
	private Integer countryId;

	@Column(name = "CREATED_DATE")
	private Date createdDate;

	@Column(name = "UPDATED_DATE")
	private Date updatedDate;

}
