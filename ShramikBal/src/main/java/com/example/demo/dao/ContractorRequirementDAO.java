package com.example.demo.dao;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Contractor;
import com.example.demo.model.ContractorRequirement;
import com.example.demo.repository.ContractorRequirementRepository;

@Component
public class ContractorRequirementDAO implements ContractorRequirementDAOInterface{

	@Autowired
	ContractorRequirementRepository contractorRequirementRepository;
	
	@Override
	public ContractorRequirement saveContractorRequirement(ContractorRequirement contractorRequirement) {
		return contractorRequirementRepository.save(contractorRequirement);
	}

	@Override
	public ContractorRequirement findByContractorRequirementId(int contractorReuirementId) {
		Optional<ContractorRequirement> conOptional = contractorRequirementRepository.findByContractorRequirementId(contractorReuirementId);
		if(conOptional.isPresent()) {
			return conOptional.get();
		} else {
			return null;
		}
	}

	@Override
	public ArrayList<ContractorRequirement> findByContractor(Contractor contractor) {
		ArrayList<ContractorRequirement> contractorRequirements = contractorRequirementRepository.findByContractorOb(contractor);
		return contractorRequirements;
	}

	@Override
	public ArrayList<ContractorRequirement> findCRByField(String field) {
		ArrayList<ContractorRequirement> contractorRequirements = contractorRequirementRepository.findByField(field);
		return contractorRequirements;
	}

	@Override
	public ArrayList<ContractorRequirement> findCRBySiteCity(String city) {
		ArrayList<ContractorRequirement> contractorRequirements = contractorRequirementRepository.findBySiteCity(city);
		return contractorRequirements;
	}

	@Override
	public ArrayList<ContractorRequirement> findByFieldAndSiteCity(String field, String city) {
		ArrayList<ContractorRequirement> contractorRequirements = contractorRequirementRepository.findByFieldAndSiteCity(field, city);
		return contractorRequirements;
	}

	@Override
	public ArrayList<ContractorRequirement> findByFieldAndSiteState(String field, String state) {
		ArrayList<ContractorRequirement> contractorRequirements = contractorRequirementRepository.findByFieldAndSiteState(field, state);
		return contractorRequirements;
	}

	@Override
	public ArrayList<ContractorRequirement> findByContractorActive(Contractor contractor) {
		ArrayList<ContractorRequirement> contractorRequirements = contractorRequirementRepository.findByContractorObAndIsActive(contractor, "Y");
		return contractorRequirements;
	}

	@Override
	public ArrayList<ContractorRequirement> findByContractorInActive(Contractor contractor) {
		ArrayList<ContractorRequirement> contractorRequirements = contractorRequirementRepository.findByContractorObAndIsActive(contractor, "N");
		return contractorRequirements;
	}

}
