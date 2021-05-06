package com.usermgmt.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.security.SecureRandom;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.usermgmt.entity.Country;
import com.usermgmt.entity.UnlockAccount;
import com.usermgmt.entity.User;
import com.usermgmt.helper.AES256;
import com.usermgmt.repository.CityMasterRepository;
import com.usermgmt.repository.CountryMasterRepository;
import com.usermgmt.repository.StateMasterRepository;
import com.usermgmt.repository.UserAccountRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private CountryMasterRepository countryRepo;
	@Autowired
	private StateMasterRepository stateRepo;
	@Autowired
	private CityMasterRepository cityRepo;
	@Autowired
	private UserAccountRepository userRepo;
	@Autowired
	private EmailService emailService;

	private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
	private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
	private static final String NUMBER = "0123456789";

	private static final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
	private static SecureRandom random = new SecureRandom();

	@Override
	public Map<Integer, String> getCountries() {
		List<Country> countryList = countryRepo.findAll();
		Map<Integer, String> countriesMap = countryList.stream()
				.collect(Collectors.toMap(Country::getCountryId, Country::getCountryName));
		return countriesMap;
	}

	@Override
	public List<Object[]> getStates(Integer countryId) {
		List<Object[]> findAllByCountryId = stateRepo.findAllByCountryId(countryId);
		return findAllByCountryId;
	}

	@Override
	public List<Object[]> getCities(Integer stateId) {
		return cityRepo.findAllByStateId(stateId);
	}

	@Override
	public String loginCheck(String email, String password) {
		Optional<User> user = userRepo.getUserByEmail(email);

		if (user.isPresent()) {
			if (password.equals(AES256.decrypt(user.get().getPassword()))) {
				if (user.get().getAccountStatus().equalsIgnoreCase("locked")) {
					return "Your account is locked state please check your email and unlock first !!";
				} else {
					return "valid";
				}
			} else {
				return "Password is wrong please re-enter correct password !!";
			}
		}
		return "Please enter registered email.";
	}

	@Override
	public boolean saveUser(User user) {
		user.setPassword(AES256.encrypt(generateRandomPassword(8)));
		user.setAccountStatus("LOCKED");
		String subject = "Unlock Your Account | UserMgmt";
		String body = getUnlockAccountEmailBody(user);

		User userSaved = userRepo.save(user);

		boolean isSent = emailService.sendAccountUnlockEmail(subject, body, user.getEmail());
		return userSaved.getUserId() != null && isSent;
	}

	@Override
	public boolean isEmailUnique(String email) {
		Integer result = userRepo.findByEmail(email);
		if (result == 0)
			return true;
		return false;
	}

	private static String generateRandomPassword(int length) {
		if (length < 1)
			throw new IllegalArgumentException();

		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {

			// 0-62 (exclusive), random returns 0-61
			int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
			char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);

			sb.append(rndChar);
		}
		return sb.toString();
	}

	private String getUnlockAccountEmailBody(User user) {
		String body = null;
		StringBuffer sb = new StringBuffer();

		try {
			File file = new File("unlock-acc-email-body.txt");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				line = br.readLine();
			}
			br.close();
			body = sb.toString();
			body = body.replace("{FNAME}", user.getFirstName());
			body = body.replace("{LNAME}", user.getLastName());
			body = body.replace("{TEMP-PWD}", AES256.decrypt(user.getPassword()));
			body = body.replace("{EMAIL}", AES256.encrypt(user.getEmail()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return body;

	}

	@Override
	public boolean isTempPasswordValid(String email, String tempPassword) {
		Optional<User> userByEmail = userRepo.getUserByEmail(email);
		if (userByEmail.isPresent()) {
			if (tempPassword.equals(AES256.decrypt(userByEmail.get().getPassword())))
				return true;
		}
		return false;
	}

	@Transactional
	public boolean unlockAccountAndUpdateStatus(UnlockAccount unlockAccount) {
		String accStatus = "UNLOCKED";
		return userRepo.updateAccountPasswordAndStatus(unlockAccount.getEmail(),
				AES256.encrypt(unlockAccount.getCnfPassword()), accStatus) > 0;
	}
}
