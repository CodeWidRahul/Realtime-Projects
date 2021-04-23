package com.phbook.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phbook.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Serializable> {

}
