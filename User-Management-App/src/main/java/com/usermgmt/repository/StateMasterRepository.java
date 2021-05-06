package com.usermgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.usermgmt.entity.State;

public interface StateMasterRepository extends JpaRepository<State, Integer> {

	@Query("select s.stateId,s.stateName from State s where s.countryId=:countryId")
	public List<Object[]> findAllByCountryId(Integer countryId);

}
