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
@Table(name = "STATE_MASTER")
public class State {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "STATE_ID")
	private Integer stateId;

	@Column(name = "STATE_NAME")
	private String stateName;

	@Column(name = "COUNTRY_ID")
	private Integer countryId;
}
