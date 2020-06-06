package com.example.demo.dao;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Labourer;
import com.example.demo.model.LoginDetails;
import com.example.demo.repository.LabourerRepository;

@Component
public class LabourersDAO implements LabourersDAOInterface{

	@Autowired
	LabourerRepository labourerRepository;
	
	public Labourer findByLoginDetails(LoginDetails loginDetails) {
		Optional<Labourer> user = labourerRepository.findByLoginDetails(loginDetails);
		if(user.isPresent()) {
			return user.get();
		}else {
			return null;
		}
	}

	@Override
	public Labourer saveLabourer(Labourer labourer) {
		Labourer user = labourerRepository.save(labourer);
		if(user!=null) {
			return user;
		}else {
			return null;
		}
	}

	@Override
	public ArrayList<Labourer> findByField(String field) {
		return labourerRepository.findByFieldOfSpecialization(field);
	}
	
	@Override
	public ArrayList<Labourer> findByCity(String city) {
		return labourerRepository.findByCity(city);
	}
	
	@Override
	public ArrayList<Labourer> findByState(String state) {
		return labourerRepository.findByState(state);
	}

	@Override
	public ArrayList<Labourer> findByFieldAndCity(String field, String city) {
		return labourerRepository.findByFieldOfSpecializationAndCity(field, city);
	}

	@Override
	public ArrayList<Labourer> findByFieldAndState(String field, String state) {
		return labourerRepository.findByFieldOfSpecializationAndState(field, state);
	}

	

}
