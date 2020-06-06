package com.example.demo.querybean;

import com.example.demo.model.Contractor;
import com.example.demo.model.Labourer;
import com.example.demo.model.LoginDetails;

import lombok.Data;

@Data
public class UserProfileBean {
	private Labourer labourer;
	private LoginDetails loginDetails;
	private Contractor contractor;
}
