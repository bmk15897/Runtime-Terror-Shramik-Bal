package com.example.demo.service;

import java.util.ArrayList;
import java.util.Map;

import com.example.demo.model.Contractor;
import com.example.demo.model.ContractorRequirement;
import com.example.demo.model.Labourer;
import com.example.demo.model.LoginDetails;
import com.example.demo.model.WorkerApplication;
import com.example.demo.querybean.ContractorRequirementProfile;
import com.example.demo.querybean.ContractorRequirementRequest;
import com.example.demo.querybean.LabourerBasicProfile;
import com.example.demo.querybean.UserProfileBean;
import com.example.demo.querybean.WorkerApplicationProfile;

public interface RequirementServiceInterface {
	ContractorRequirement addContractorRequirement(ContractorRequirementRequest contractorRequirement);
	WorkerApplication addWorkerApplication(int contractorRequirement, String labourer, String createdDate);
	WorkerApplication approveWorkerApplication(int workerApplication);
	WorkerApplication declineWorkerApplication(int workerApplication);
	ArrayList<WorkerApplicationProfile> findWAByLabourer(String labourer);
	ArrayList<WorkerApplicationProfile> findWAByLabourerHistory(String labourer);
	ArrayList<WorkerApplication> findWAByContractorRequirementId(int contractorRequirementId);
	ArrayList<ContractorRequirement> findCRByContractor(String contractorId);
	ArrayList<ContractorRequirement> findCRBySiteCity(String city);
	ArrayList<ContractorRequirementProfile> findCRByField(String field);
	ArrayList<ContractorRequirementProfile> findCRByFieldAndSiteCity(String field,String city);
	Labourer findLabourerById(String username);
	Contractor findContractorById(String username);
	ArrayList<LabourerBasicProfile> findLabourersByField(String field);
	ArrayList<LabourerBasicProfile> findLabourersByCity(String city);
	ArrayList<LabourerBasicProfile> findLabourersByState(String state);
	ArrayList<LabourerBasicProfile> findLabourersByFieldAndCity(String field, String city);
	ArrayList<LabourerBasicProfile> findLabourersByFieldAndState(String field, String state);
	ArrayList<WorkerApplicationProfile> findWAByContractor(String contractorUsername);
	UserProfileBean updateProfileFields(UserProfileBean userProfileBean);
	UserProfileBean updateProfilePassword(Map<String, String> values);
	ArrayList<ContractorRequirement> findActiveCRByContractor(String contractorId);
	ArrayList<ContractorRequirement> findInActiveCRByContractor(String contractorId);
	ArrayList<ContractorRequirementProfile> findCRByFieldAndSiteState (String field,String state);
}