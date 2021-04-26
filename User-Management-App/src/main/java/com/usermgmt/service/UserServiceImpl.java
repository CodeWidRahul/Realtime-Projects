package com.usermgmt.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usermgmt.entity.Country;
import com.usermgmt.repository.CountryMasterRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private CountryMasterRepository countryRepo;

	@Override
	public Map<Integer, String> getCountries() {
		List<Country> countryList = countryRepo.findAll();
		Map<Integer, String> countriesMap = countryList.stream()
				.collect(Collectors.toMap(Country::getCountryId, Country::getCountryName));
		return countriesMap;
	}

	@Override
	public Map<Integer, String> getStates(Integer countryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Integer, String> getCities(Integer stateId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String loginCheck(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
