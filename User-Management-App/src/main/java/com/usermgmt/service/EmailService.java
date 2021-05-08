package com.usermgmt.service;

public interface EmailService {

	public boolean sendEmail(String subject, String body, String to);
}
