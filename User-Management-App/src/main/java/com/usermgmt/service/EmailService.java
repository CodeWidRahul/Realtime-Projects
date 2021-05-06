package com.usermgmt.service;

public interface EmailService {

	public boolean sendAccountUnlockEmail(String subject, String body, String to);
}
