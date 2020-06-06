package com.example.demo.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Contractor;
import com.example.demo.model.ContractorRequirement;

public interface ContractorRequirementRepository  extends JpaRepository<ContractorRequirement, Integer> {
	ContractorRequirement save(ContractorRequirement contractorRequirement);
	Optional<ContractorRequirement> findByContractorRequirementId(int contractorReqId);
	ArrayList<ContractorRequirement> findByContractorOb(Contractor contractor);
	ArrayList<ContractorRequirement> findBySiteCity(String city);
	ArrayList<ContractorRequirement> findByField(String field);
	ArrayList<ContractorRequirement> findByFieldAndSiteCity(String field,String city);
	ArrayList<ContractorRequirement> findByContractorObAndIsActive(Contractor contractor,String isActive);
	ArrayList<ContractorRequirement> findByFieldAndSiteState(String field,String state);
}
