package com.example.demo.dao;

import java.util.ArrayList;

import com.example.demo.model.ContractorRequirement;
import com.example.demo.model.Labourer;
import com.example.demo.model.WorkerApplication;

public interface WorkerApplicationDAOInterface {
	WorkerApplication saveWorkerApplication(WorkerApplication workerApplication);
	WorkerApplication findWorkerApplication(int workAppID);
	ArrayList<WorkerApplication> findByLabourer(Labourer labourer);
	ArrayList<WorkerApplication> findByContractorRequirementId(ContractorRequirement contractorRequirement);
	ArrayList<Integer> findContractorRequirementIdsByLabourer(Labourer labourer);
}
