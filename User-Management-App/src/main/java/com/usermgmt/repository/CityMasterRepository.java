package com.usermgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.usermgmt.entity.City;

public interface CityMasterRepository extends JpaRepository<City, Integer> {

	@Query("select c.cityId,c.cityName from City c where c.stateId=:stateId")
	public List<Object[]> findAllByStateId(Integer stateId);
}
