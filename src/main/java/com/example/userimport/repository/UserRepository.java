package com.example.userimport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.userimport.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	public User findOneById(Integer userId);
	
	public User findByUserName(String userName);

}
