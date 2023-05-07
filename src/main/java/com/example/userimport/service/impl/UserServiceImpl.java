package com.example.userimport.service.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.example.userimport.entity.User;
import com.example.userimport.entity.utils.Constants;
import com.example.userimport.repository.UserRepository;
import com.example.userimport.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> saveAll(List<User> users) {
		return userRepository.saveAll(users);
	}

	@Override
	public User update(Integer userId, User user) {
		return userRepository.findOneById(userId);
	}

	@Override
	public User findOneById(Integer userId) {
		return userRepository.findOneById(userId);
	}
	
	public User findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	
	@Override
	public Map<String, List<Map<String, Object>>> validateUserDetails(List<User> users) {
		// Store the validation status to return
		Map<String, List<Map<String, Object>>> statusMap = new HashMap<>();
		
		List<Map<String, Object>> passedStatusMapList = new LinkedList<>();
		List<Map<String, Object>> failedStatusMapList = new LinkedList<>();
		
		for(User user : users) {
			Map<String, Object> detailsMap = new HashMap<>();
			detailsMap.put(Constants.STATUS, Constants.PASSED);
			
			// validate user data is not empty
			if (ObjectUtils.isEmpty(user.getUserName())) {
				detailsMap.put(Constants.STATUS, Constants.FAILED);
				detailsMap.put(Constants.FAILED_REASON, Constants.USERNAME_EMPTY);
			} else if (ObjectUtils.isEmpty(user.getEmail())) {
				detailsMap.put(Constants.FAILED_REASON, Constants.FAILED);
				detailsMap.put(Constants.FAILED_REASON, Constants.EMAIL_EMPTY);
			} else if (ObjectUtils.isEmpty(user.getPhoneNumber())) {
				detailsMap.put(Constants.FAILED_REASON, Constants.FAILED);
				detailsMap.put(Constants.FAILED_REASON, Constants.PHONE_NUMBER_EMPTY);
			}

			if (detailsMap.get(Constants.STATUS).equals(Constants.PASSED)) {
				// check if user data is valid
				Matcher userNameMatcher = Constants.USER_NAME_REGEX.matcher(user.getUserName());
				Matcher emailMatcher = Constants.EMAIL_ADDRESS_REGEX.matcher(user.getEmail());
				Matcher phoneNumberMatcher = Constants.PHONE_NUMBER_REGEX.matcher(user.getPhoneNumber());

				if (!userNameMatcher.matches()) {
					detailsMap.put(Constants.STATUS, Constants.FAILED);
					detailsMap.put(Constants.FAILED_REASON, Constants.USER_NAME_NOT_VALID);
				} else if (!emailMatcher.matches()) {
					detailsMap.put(Constants.STATUS, Constants.FAILED);
					detailsMap.put(Constants.FAILED_REASON, Constants.EMAIL_NOT_VALID);
				} else if (!phoneNumberMatcher.matches()) {
					detailsMap.put(Constants.STATUS, Constants.FAILED);
					detailsMap.put(Constants.FAILED_REASON, Constants.PHONE_NUMBER_NOT_VALID);
				}

			}	
			
			if (detailsMap.get(Constants.STATUS).equals(Constants.PASSED)) {
				detailsMap.remove(Constants.STATUS);
				detailsMap.put(Constants.USER_NAME,user.getUserName());
				detailsMap.put(Constants.USER_DETAILS, user);
				passedStatusMapList.add(detailsMap);
			}else {
				detailsMap.remove(Constants.STATUS);
				detailsMap.put(Constants.USER_NAME,user.getUserName());
				detailsMap.put(Constants.FAILED_REASON, detailsMap.get(Constants.FAILED_REASON));
				detailsMap.put(Constants.USER_DETAILS, user);
				failedStatusMapList.add(detailsMap);
			}

		}

		statusMap.put(Constants.PASSED, passedStatusMapList);
		statusMap.put(Constants.FAILED, failedStatusMapList);
		return statusMap;
	}
}
