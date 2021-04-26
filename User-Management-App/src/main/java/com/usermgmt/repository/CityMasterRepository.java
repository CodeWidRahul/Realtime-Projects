package com.usermgmt.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usermgmt.entity.City;

public interface CityMasterRepository extends JpaRepository<City, Serializable> {

}
