package com.usermgmt.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usermgmt.entity.User;

public interface UserAccountRepository extends JpaRepository<User, Serializable> {

}
