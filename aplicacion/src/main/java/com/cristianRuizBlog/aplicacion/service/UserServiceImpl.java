package com.cristianRuizBlog.aplicacion.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cristianRuizBlog.aplicacion.entity.User;
import com.cristianRuizBlog.aplicacion.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	
	
	private boolean checkUsernameAvaiable(User user) throws Exception {
		Optional<User> userFound = userRepository.findByUsername(user.getUsername());
		if(userFound.isPresent()) {
			throw new Exception("Username no disponible");
		}
		return true;
	}
	
	private boolean checkPasswordValid(User user) throws Exception {
		if(!user.getPassword().equals(user.getConfirmPassword())) {
			throw new Exception("Password y Confirm Password no son iguales");
		}
		return true;
	}


	@Override
	public User createUser(User user) throws Exception {
		if(checkUsernameAvaiable(user) && checkPasswordValid(user)) {
			user = userRepository.save(user);
		}
		return user;
	}

}
