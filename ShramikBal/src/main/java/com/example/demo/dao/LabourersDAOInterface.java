package com.example.demo.dao;

import java.util.ArrayList;

import com.example.demo.model.Labourer;
import com.example.demo.model.LoginDetails;

public interface LabourersDAOInterface {
	Labourer findByLoginDetails(LoginDetails loginDetails);
	Labourer saveLabourer(Labourer labourer);
	ArrayList<Labourer> findByField(String field);
	ArrayList<Labourer> findByCity(String city);
	ArrayList<Labourer> findByState(String state);
	ArrayList<Labourer> findByFieldAndCity(String field,String city);
	ArrayList<Labourer> findByFieldAndState(String field,String state);
}
