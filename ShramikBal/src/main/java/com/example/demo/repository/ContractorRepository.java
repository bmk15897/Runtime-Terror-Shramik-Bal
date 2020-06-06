package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Contractor;
import com.example.demo.model.LoginDetails;

@Repository
public interface ContractorRepository extends JpaRepository<Contractor, Integer>{
	Optional<Contractor> findByLoginDetails(LoginDetails loginDetails);
	Contractor save(Contractor contractor);
}
