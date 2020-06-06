package com.example.demo.dao;

import com.example.demo.model.Contractor;
import com.example.demo.model.LoginDetails;

public interface ContractorsDAOInterface {
	Contractor findByLoginDetails(LoginDetails loginDetails);
	Contractor saveContractor(Contractor contractor);
}
