package com.example.demo.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import com.example.demo.querybean.UserProfileBean;

public interface LoginServiceInterface {
	UserProfileBean findUserByUserName(String userName);
	UserProfileBean saveUser(UserProfileBean userProfileBean) throws NoSuchAlgorithmException, InvalidKeySpecException;
}