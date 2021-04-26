package com.usermgmt.service;

import java.util.Map;

public interface UserService {

	public Map<Integer, String> getCountries();

	public Map<Integer, String> getStates(Integer countryId);

	public Map<Integer, String> getCities(Integer stateId);

	public String loginCheck(String email, String password);
}
