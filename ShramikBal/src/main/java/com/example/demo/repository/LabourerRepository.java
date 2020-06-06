package com.example.demo.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Labourer;
import com.example.demo.model.LoginDetails;

@Repository
public interface LabourerRepository extends JpaRepository<Labourer, Integer>{
	Optional<Labourer> findByLoginDetails(LoginDetails loginDetails);
	Labourer save(Labourer labourer);
	ArrayList<Labourer> findByFieldOfSpecialization(String field);
	ArrayList<Labourer> findByCity(String city);
	ArrayList<Labourer> findByState(String state);
	ArrayList<Labourer> findByFieldOfSpecializationAndCity(String field, String city);
	ArrayList<Labourer> findByFieldOfSpecializationAndState(String field, String state);
}
