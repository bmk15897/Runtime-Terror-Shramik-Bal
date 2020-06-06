package com.example.demo.dao;

import com.example.demo.model.LoginDetails;

public interface LoginDetailsDAOInterface {
	LoginDetails findByUserName(String username);
	LoginDetails saveLoginDetails(LoginDetails loginDetails);
}
