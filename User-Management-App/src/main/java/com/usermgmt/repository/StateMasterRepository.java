package com.usermgmt.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usermgmt.entity.State;

public interface StateMasterRepository extends JpaRepository<State, Serializable> {

}
