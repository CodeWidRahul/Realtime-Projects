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
	@Column(name = "USER_ID", length = 4)
	private Integer userId;

	@Column(name = "FIRST_NAME", length = 20, nullable = false)
	private String firstName;

	@Column(name = "LAST_NAME", length = 20)
	private String lastName;

	@Column(name = "DOB", length = 30)
	private Date dob;

	@Column(name = "GENDER", length = 6)
	private String gender;

	@Column(name = "EMAIL", length = 40, nullable = false, unique = true)
	private String email;

	@Column(name = "PASSWORD", length = 30)
	private String password;

	@Column(name = "PHONE_NO", length = 10, nullable = false)
	private Long phoneNo;

	@Column(name = "ACC_STATUS", length = 8)
	private String accountStatus;

	@Column(name = "CITY_ID", length = 3)
	private Integer cityId;

	@Column(name = "STATE_ID", length = 3)
	private Integer stateId;

	@Column(name = "COUNTRY_ID", length = 3)
	private Integer countryId;

	@Column(name = "CREATED_DATE", length = 30)
	private Date createdDate;

	@Column(name = "UPDATED_DATE", length = 30)
	private Date updatedDate;

}
