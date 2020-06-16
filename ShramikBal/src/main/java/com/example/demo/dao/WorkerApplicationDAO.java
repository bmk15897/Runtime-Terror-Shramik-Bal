package com.example.demo.dao;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.ContractorRequirement;
import com.example.demo.model.Labourer;
import com.example.demo.model.WorkerApplication;
import com.example.demo.repository.WorkerApplicationRepository;

@Component
public class WorkerApplicationDAO implements WorkerApplicationDAOInterface{

	@Autowired
	WorkerApplicationRepository workerApplicationRepository;
	
	@Override
	public WorkerApplication saveWorkerApplication(WorkerApplication workerApplication) {
		return workerApplicationRepository.save(workerApplication);
	}

	@Override
	public WorkerApplication findWorkerApplication(int workAppID) {
		Optional<WorkerApplication> application = workerApplicationRepository.findByWorkerApplicationId(workAppID);
		if(application.isPresent()) {
			return application.get();
		}else {
			return null;
		}
	}

	@Override
	public ArrayList<WorkerApplication> findByLabourer(Labourer labourer) {
		ArrayList<WorkerApplication> workerApplications = workerApplicationRepository.findByLabourer(labourer);
		return workerApplications;
	}

	@Override
	public ArrayList<WorkerApplication> findByContractorRequirementId(ContractorRequirement contractorRequirement) {
		ArrayList<WorkerApplication> workerApplications = workerApplicationRepository.findByContractorRequirementId(contractorRequirement);
		return workerApplications;
	}

	@Override
	public ArrayList<Integer> findContractorRequirementIdsByLabourer(Labourer labourer) {
		ArrayList<Integer> workerApplicationIds = workerApplicationRepository.findWorkerApplicationIdsByLabourer(labourer);
		return workerApplicationIds;
	}

}
