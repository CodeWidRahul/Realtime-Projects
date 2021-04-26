package com.usermgmt.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usermgmt.entity.Country;

public interface CountryMasterRepository extends JpaRepository<Country, Serializable> {

}
