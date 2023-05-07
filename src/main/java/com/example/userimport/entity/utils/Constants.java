package com.example.userimport.entity.utils;

import java.util.regex.Pattern;

public class Constants {
	
	public static final String USER_NAME = "USER_NAME";

	public static final String USER_DETAILS = "USER_DETAILS";

	public static final String STATUS = "STATUS";

	public static final String PASSED = "PASSED";

	public static final String FAILED = "FAILED";

	public static final String FAILED_REASON = "FAILED_REASON";

	public static final String USERNAME_EMPTY = "User Name is empty";

	public static final String EMAIL_EMPTY = "Email is empty";

	public static final String PHONE_NUMBER_EMPTY = "Phone number is empty";

	public static final Pattern USER_NAME_REGEX = Pattern.compile("^[A-Za-z]\\w{5,29}$");

	public static final Pattern EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	public static final Pattern PHONE_NUMBER_REGEX = Pattern.compile("^\\d{10}$");

	public static final String USER_NAME_NOT_VALID = "User Name is not valid";

	public static final String EMAIL_NOT_VALID = "Email is not valid";

	public static final String PHONE_NUMBER_NOT_VALID = "Phone number is not valid";	
	
}
