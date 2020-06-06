package com.example.demo.service;

import com.example.demo.querybean.UserProfileBean;

public interface LoginServiceInterface {
	UserProfileBean findUserByUserName(String userName);
	UserProfileBean saveUser(UserProfileBean userProfileBean);
}