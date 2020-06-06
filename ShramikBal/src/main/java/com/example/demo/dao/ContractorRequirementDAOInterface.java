package com.example.demo.dao;

import java.util.ArrayList;

import com.example.demo.model.Contractor;
import com.example.demo.model.ContractorRequirement;

public interface ContractorRequirementDAOInterface {
	ContractorRequirement saveContractorRequirement(ContractorRequirement contractorRequirement);
	ContractorRequirement findByContractorRequirementId(int contractorReuirementId);
	ArrayList<ContractorRequirement> findByContractor(Contractor contractor);
	ArrayList<ContractorRequirement> findCRBySiteCity(String city);
	ArrayList<ContractorRequirement> findByFieldAndSiteCity(String field, String city);
	ArrayList<ContractorRequirement> findByContractorActive(Contractor contractor);
	ArrayList<ContractorRequirement> findByContractorInActive(Contractor contractor);
	ArrayList<ContractorRequirement> findByFieldAndSiteState(String field, String state);
	ArrayList<ContractorRequirement> findCRByField(String field);
}
