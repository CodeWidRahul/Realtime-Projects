package com.usermgmt.service;

import java.util.Map;

public interface UserService {

	public Map<Integer, String> getCountries();

	public Map<Integer, String> getStates();

	public Map<Integer, String> getCities();

	public String loginCheck(String email, String password);
}
