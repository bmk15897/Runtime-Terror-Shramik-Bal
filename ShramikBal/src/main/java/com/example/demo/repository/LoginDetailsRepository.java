package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.LoginDetails;


@Repository
public interface LoginDetailsRepository extends JpaRepository<LoginDetails, Integer> {

	Optional<LoginDetails> findByUserName(String username);
	LoginDetails save(LoginDetails loginDetails);
}
