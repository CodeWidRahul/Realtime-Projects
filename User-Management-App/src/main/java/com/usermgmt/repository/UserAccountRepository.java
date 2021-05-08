package com.usermgmt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.usermgmt.entity.User;

public interface UserAccountRepository extends JpaRepository<User, Integer> {

	@Query("SELECT COUNT(*) FROM User u WHERE u.email=:email")
	public Integer findByEmail(String email);

	@Query("SELECT u FROM User u WHERE u.email=:email")
	public Optional<User> getUserByEmail(String email);

	@Modifying
	@Query("UPDATE User u SET u.password=:cnfPassword,u.accountStatus=:accStatus WHERE u.email=:email")
	public Integer updateAccountPasswordAndStatus(String email, String cnfPassword, String accStatus);

	@Query("SELECT password FROM User u WHERE u.email=:email")
	public String getPasswordByEmail(String email);

}
