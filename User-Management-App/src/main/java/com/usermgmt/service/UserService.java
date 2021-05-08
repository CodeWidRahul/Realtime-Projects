package com.usermgmt.service;

import java.util.List;
import java.util.Map;

import com.usermgmt.entity.UnlockAccount;
import com.usermgmt.entity.User;

public interface UserService {

	public Map<Integer, String> getCountries();

	public List<Object[]> getStates(Integer countryId);

	public List<Object[]> getCities(Integer stateId);

	public Object loginCheck(String email, String password);

	public boolean saveUser(User user);

	public boolean isEmailUnique(String email);

	public boolean isTempPasswordValid(String email, String tempPassword);

	public boolean unlockAccountAndUpdateStatus(UnlockAccount unlockAccount);

	public boolean forgetPasswordAndSendMail(String email);
}
