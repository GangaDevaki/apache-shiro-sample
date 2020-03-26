package com.bny.esg.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bny.esg.entity.User;
import com.bny.esg.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User getUser(String id) {
		return userRepository.findById(id);
	}
	
	public User getUserByName(String userName) {
		return userRepository.findByUsername(userName);
	}
	
	public Collection<User> listUsers() {
		return userRepository.findAll();
	}

	public void deleteUser(String id) {
		userRepository.deleteUserById(id);
	}

	public User updateUser(String id, User updateUser) {
		return null;
	}

	public User addUser(User user) {
		return null;
	}
}