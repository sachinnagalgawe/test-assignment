package com.example.userimport.entity.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.example.userimport.entity.User;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

public class Utils {

	public static List<User> parse(File file) {
		List<User> UserList = null;
		try {
			// Skiping firstline as it is header
			CSVReader reader = new CSVReaderBuilder(new FileReader(file)).withSkipLines(1).build();
			try {
				UserList = reader.readAll().stream().map(data -> {
					User csvUser = new User();
					csvUser.setUserName(data[0]);
					csvUser.setEmail(data[1]);
					csvUser.setPhoneNumber(data[2]);
					return csvUser;
				}).collect(Collectors.toList());
			} catch (CsvException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//UserList.forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return UserList;
	}
}
