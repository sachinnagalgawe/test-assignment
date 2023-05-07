package com.example.userimport.service;

import java.util.List;
import java.util.Map;

import com.example.userimport.entity.User;

public interface UserService {

	public User save(User user);

	public List<User> saveAll(List<User> users);

	public User update(Integer userId, User user);
	
	public User findOneById(Integer userId);
	
	public User findByUserName(String userName);

	public Map<String, List<Map<String, Object>>> validateUserDetails(List<User> users);
}
