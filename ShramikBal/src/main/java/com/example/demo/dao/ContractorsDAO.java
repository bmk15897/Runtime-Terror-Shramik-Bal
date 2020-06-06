package com.example.demo.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Contractor;
import com.example.demo.model.LoginDetails;
import com.example.demo.repository.ContractorRepository;

@Component
public class ContractorsDAO implements ContractorsDAOInterface{

	@Autowired
	ContractorRepository contractorRepository;
	
	@Override
	public Contractor findByLoginDetails(LoginDetails loginDetails) {
		Optional<Contractor> user = contractorRepository.findByLoginDetails(loginDetails);
		if(user.isPresent()) {
			return user.get();
		}else {
			return null;
		}
	}

	@Override
	public Contractor saveContractor(Contractor contractor) {
		Contractor user = contractorRepository.save(contractor);
		if(user!=null) {
			return user;
		}else {
			return null;
		}
	}

}
