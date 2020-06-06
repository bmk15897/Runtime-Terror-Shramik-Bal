package com.example.demo.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.LoginDetails;
import com.example.demo.repository.LoginDetailsRepository;

@Component
public class LoginDetailsDAO implements LoginDetailsDAOInterface{

	@Autowired
	LoginDetailsRepository loginDetailsRepository;
	
	@Override
	public LoginDetails findByUserName(String username) {
		Optional<LoginDetails> user = loginDetailsRepository.findByUserName(username);
		if(user.isPresent()) {
			return user.get();
		}else {
			return null;
		}
	}

	@Override
	public LoginDetails saveLoginDetails(LoginDetails loginDetails) {
		LoginDetails user = loginDetailsRepository.save(loginDetails);
		if(user!=null) {
			return user;
		}else {
			return null;
		}
	}

}
