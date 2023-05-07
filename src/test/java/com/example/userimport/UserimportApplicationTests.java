package com.example.userimport;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.userimport.entity.User;
import com.example.userimport.entity.utils.Constants;
import com.example.userimport.entity.utils.Utils;
import com.example.userimport.service.UserService;

@SpringBootTest
class UserimportApplicationTests {

	org.slf4j.Logger logger = LoggerFactory.getLogger(UserimportApplicationTests.class);

	@Value("${csv.file.name}")
	private String csvFileName;
	
	@Autowired
	private UserService userService;

	@Test
	void userTest() {
		File file = new File(getClass().getResource("/"+csvFileName).getFile());
		List<User> UserList = Utils.parse(file);
		
		Map<String, List<Map<String, Object>>> result = userService.validateUserDetails(UserList);
		List<Map<String, Object>> passedStatusMapList = result.get(Constants.PASSED);
		
		
		//Report Details Related Maps
		// Basic validation failed details list
		List<Map<String, Object>> failedStatusMapList = result.get(Constants.FAILED);
		// Newly users created details list
		List<User> newlyAddedUsersList = new LinkedList<>();
		// Newly users updated details list
		List<User> newlyUpdatedUsersList = new LinkedList<>();

		
		for (Map<String, Object> passedMap : passedStatusMapList) {
			User user = (User) passedMap.get(Constants.USER_DETAILS);
			User existingUser = userService.findByUserName(user.getUserName());
			if (existingUser == null) {
				newlyAddedUsersList.add(userService.save(user));
			} else {
				existingUser.setEmail(user.getEmail());
				existingUser.setPhoneNumber(user.getPhoneNumber());
				newlyUpdatedUsersList.add(userService.save(existingUser));
			}
		}

		System.out.println("*******************************************************************");
		System.out.println("\n");
		System.out.println("Report details for csv file: "+csvFileName);
		System.out.println("From CSV file Total Users: "+(failedStatusMapList.size()+passedStatusMapList.size()));
		System.out.println("From CSV file Total validation Failed Users: "+failedStatusMapList.size());
		System.out.println("From CSV file Total validation Passed Users: "+passedStatusMapList.size());
		System.out.println("From CSV file Total validation Passed and new Users: "+newlyAddedUsersList.size());
		System.out.println("From CSV file Total validation Passed and duplicate Users: "+newlyUpdatedUsersList.size());
		System.out.println("\n");
		System.out.println("*******************************************************************");
		System.out.println("			All Validation Failed User Details And Reason 			");
		System.out.println("*******************************************************************");
		for(Map<String, Object> failedUser: failedStatusMapList) {
			System.out.println(failedUser.toString());
		}
		System.out.println("\n");
		System.out.println("*******************************************************************");
		System.out.println("			All Validation Passed User Details 		 			");
		System.out.println("*******************************************************************");
		for(Map<String, Object> passedUser: passedStatusMapList) {
			System.out.println(passedUser.toString());
		}
		System.out.println("\n");
		System.out.println("*******************************************************************");
		System.out.println("			Newly Added Validation Passed Users 		 			");
		System.out.println("*******************************************************************");
		for(User newUser: newlyAddedUsersList) {
			System.out.println(newUser.toString());
		}
		System.out.println("\n");
		System.out.println("*******************************************************************");
		System.out.println("			Validation Passed And Duplicate Users 		 			");
		System.out.println("*******************************************************************");
		for(User newUser: newlyUpdatedUsersList) {
			System.out.println(newUser.toString());
		}
		System.out.println("\n");
		System.out.println("*******************************************************************");	
	}
}
