package com.example.demo.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ContractorRequirementDAO;
import com.example.demo.dao.ContractorsDAO;
import com.example.demo.dao.LabourersDAO;
import com.example.demo.dao.LoginDetailsDAO;
import com.example.demo.dao.WorkerApplicationDAO;
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
import com.example.demo.supportAlgorithms.PwdHasher;

@Service
public class RequirementService implements RequirementServiceInterface{

	@Autowired
	ContractorRequirementDAO contractorRequirementDAO;
	
	@Autowired
	WorkerApplicationDAO workerApplicationDAO;
	
	@Autowired
	ContractorsDAO contractorsDAO;
	
	@Autowired
	LoginDetailsDAO loginDetailsDAO;
	
	@Autowired
	LabourersDAO labourersDAO;
	
	@Override
	public Labourer findLabourerById(String username) {
		LoginDetails loginDetails = loginDetailsDAO.findByUserName(username);
		if(loginDetails!=null) {
			Labourer labourer = labourersDAO.findByLoginDetails(loginDetails);
			return labourer;
		} else {
			return null;
		}
	}

	@Override
	public Contractor findContractorById(String username) {
		LoginDetails loginDetails = loginDetailsDAO.findByUserName(username);
		if(loginDetails!=null) {
			Contractor contractor = contractorsDAO.findByLoginDetails(loginDetails);
			System.out.println(contractor);
			return contractor;
		} else {
			return null;
		}
	}

	@Override
	public UserProfileBean updateProfileFields(UserProfileBean userProfileBean) {
		LoginDetails loginDetails = loginDetailsDAO.findByUserName(userProfileBean.getLoginDetails().getUserName());
		if(loginDetails.getType().equals("L")) {
			Labourer updatedLabourer = userProfileBean.getLabourer();
			Labourer labourer = labourersDAO.findByLoginDetails(loginDetails);
			labourer.setAddress(updatedLabourer.getAddress());
			labourer.setName(updatedLabourer.getName());
			labourer.setAge(updatedLabourer.getAge());
			labourer.setGender(updatedLabourer.getGender());
			labourer.setCity(updatedLabourer.getCity());
			labourer.setState(updatedLabourer.getState());
			labourer.setContactNo(updatedLabourer.getContactNo());
			labourer.setFieldOfSpecialization(updatedLabourer.getFieldOfSpecialization());
			labourer.setGroupCount(updatedLabourer.getGroupCount());
			labourer.setPersonOrGroup(updatedLabourer.getPersonOrGroup());
			userProfileBean.setLabourer(labourersDAO.saveLabourer(labourer));
		} else {
			Contractor contractor = contractorsDAO.findByLoginDetails(loginDetails);
			Contractor updatedcontractor = userProfileBean.getContractor();
			contractor.setAddress(updatedcontractor.getAddress());
			contractor.setName(updatedcontractor.getName());
			contractor.setGender(updatedcontractor.getGender());
			contractor.setAge(updatedcontractor.getAge());
			contractor.setCity(updatedcontractor.getCity());
			contractor.setState(updatedcontractor.getState());
			contractor.setContactNo(updatedcontractor.getContactNo());
			userProfileBean.setContractor(contractorsDAO.saveContractor(contractor));
		}
		return userProfileBean;
	}

	@Override
	public UserProfileBean updateProfilePassword(Map<String, String> values) throws NoSuchAlgorithmException, InvalidKeySpecException {
		UserProfileBean userProfileBean = new UserProfileBean();
		String oldPassword = values.get("oldPassword");
		String newPassword = values.get("newPassword");
		String username = values.get("userName");
		LoginDetails loginDetails = loginDetailsDAO.findByUserName(username);
		if(PwdHasher.validatePassword(oldPassword,loginDetails.getPassword())) {
			loginDetails.setPassword(PwdHasher.generateStorngPasswordHash(newPassword));
			loginDetailsDAO.saveLoginDetails(loginDetails);
			if(loginDetails.getType().equals("L")) {
				Labourer labourer = labourersDAO.findByLoginDetails(loginDetails);
				userProfileBean.setLabourer(labourer);
			}else {
				Contractor contractor = contractorsDAO.findByLoginDetails(loginDetails);
				userProfileBean.setContractor(contractor);
			}
			loginDetails.setPassword(null);
			userProfileBean.setLoginDetails(loginDetails);
			return userProfileBean;
		}else {
			return null;
		}
		
	}

	@Override
	public ContractorRequirement addContractorRequirement(ContractorRequirementRequest
			contractorRequirementRequest) {
	System.out.println(contractorRequirementRequest);
		LoginDetails loginDetails = loginDetailsDAO.findByUserName(contractorRequirementRequest.getContractorUserName());
		Contractor contractor = contractorsDAO.findByLoginDetails(loginDetails);
		
		ContractorRequirement contractorRequirement = new ContractorRequirement();
		contractorRequirement.setContractorOb(contractor);
		contractorRequirement.setField(contractorRequirementRequest.getField());
		contractorRequirement.setNoOfPeople(contractorRequirementRequest.getNoOfPeople());
		contractorRequirement.setNoOfPeopleApplied(0);
		contractorRequirement.setSiteAddress(contractorRequirementRequest.getSiteAddress());
		contractorRequirement.setSiteCity(contractorRequirementRequest.getSiteCity());
		contractorRequirement.setDescription(contractorRequirementRequest.getDescription());
		contractorRequirement.setSiteState(contractorRequirementRequest.getSiteState());
		contractorRequirement.setIsActive("Y");
		contractorRequirement.setCreatedDate(contractorRequirementRequest.getCreatedDate());
		return contractorRequirementDAO.saveContractorRequirement(contractorRequirement);
	}

	@Override
	public WorkerApplication addWorkerApplication(int contractorRequirementId, String labourerUName, String createdDate) {
		LoginDetails loginDetails = loginDetailsDAO.findByUserName(labourerUName);
		if(loginDetails!=null){
			Labourer labourer = labourersDAO.findByLoginDetails(loginDetails);
			ContractorRequirement contractorRequirement = contractorRequirementDAO.findByContractorRequirementId(contractorRequirementId);
			WorkerApplication workerApplication = new WorkerApplication();
			workerApplication.setApproved("P");
			workerApplication.setLabourer(labourer);
			workerApplication.setCreatedDate(createdDate);
			workerApplication.setContractorRequirementId(contractorRequirement);
			workerApplication = workerApplicationDAO.saveWorkerApplication(workerApplication);
			System.out.println(workerApplication.getWorkerApplicationId());
			return workerApplication;
		} else {
			return null;
		}
		
	}

	@Override
	public WorkerApplication approveWorkerApplication(int workerApplicationId) {
		WorkerApplication workerApplication2 = workerApplicationDAO.findWorkerApplication(workerApplicationId);
		ContractorRequirement contractorRequirement = contractorRequirementDAO.findByContractorRequirementId(workerApplication2.getContractorRequirementId().getContractorRequirementId());
		if((contractorRequirement.getNoOfPeopleApplied()+workerApplication2.getLabourer().getGroupCount())<=contractorRequirement.getNoOfPeople()) {
			contractorRequirement.setNoOfPeopleApplied(contractorRequirement.getNoOfPeopleApplied()+workerApplication2.getLabourer().getGroupCount());
			if(contractorRequirement.getNoOfPeopleApplied()==contractorRequirement.getNoOfPeople())
			{
				contractorRequirement.setIsActive("N");
				Contractor contractor = contractorRequirement.getContractorOb();
				contractor.setNoOfServicesUsed(contractor.getNoOfServicesUsed()+1);
				contractorsDAO.saveContractor(contractor);
			}
			Labourer labourer = workerApplication2.getLabourer();
			labourer.setNoOfServicesProvided(labourer.getNoOfServicesProvided()+1);
			labourersDAO.saveLabourer(labourer);
			workerApplication2.setApproved("Y");
			workerApplication2 = workerApplicationDAO.saveWorkerApplication(workerApplication2);
			contractorRequirementDAO.saveContractorRequirement(contractorRequirement);
			return workerApplication2;
		}else {
			return null;
		}
	}
	
	@Override
	public WorkerApplication declineWorkerApplication(int workerApplicationId) {
		WorkerApplication workerApplication2 = workerApplicationDAO.findWorkerApplication(workerApplicationId);
		workerApplication2.setApproved("N");
		workerApplication2 = workerApplicationDAO.saveWorkerApplication(workerApplication2);
		return workerApplication2;
		
	}

	@Override
	public ArrayList<LabourerBasicProfile> findLabourersByField(String field) {
		ArrayList<Labourer> labourers = labourersDAO.findByField(field);
		ArrayList<LabourerBasicProfile> labourerBasicProfiles = new ArrayList<>();
		for(Labourer labourer:labourers) {
			LabourerBasicProfile basicProfile = new LabourerBasicProfile();
			basicProfile.setAddress(labourer.getAddress());
			basicProfile.setAge(labourer.getAge());
			basicProfile.setGender(labourer.getGender());
			basicProfile.setAvgRating(labourer.getAvgRating());
			basicProfile.setCity(labourer.getCity());
			basicProfile.setUsername(labourer.getLoginDetails().getUserName());
			basicProfile.setFieldOfSpecialization(labourer.getFieldOfSpecialization());
			basicProfile.setGroupCount(labourer.getGroupCount());
			basicProfile.setPersonOrGroup(labourer.getPersonOrGroup());
			basicProfile.setImageUrl(labourer.getImageUrl());
			basicProfile.setNoOfServicesProvided(labourer.getNoOfServicesProvided());
			basicProfile.setWithUsSince(labourer.getWithUsSince());
			basicProfile.setName(labourer.getName());
			basicProfile.setState(labourer.getState());
			labourerBasicProfiles.add(basicProfile);
		}
		return labourerBasicProfiles;
	}

	@Override
	public ArrayList<LabourerBasicProfile> findLabourersByCity(String city) {
		ArrayList<Labourer> labourers = labourersDAO.findByCity(city);
		ArrayList<LabourerBasicProfile> labourerBasicProfiles = new ArrayList<>();
		for(Labourer labourer:labourers) {
			LabourerBasicProfile basicProfile = new LabourerBasicProfile();
			basicProfile.setAddress(labourer.getAddress());
			basicProfile.setAge(labourer.getAge());
			basicProfile.setGender(labourer.getGender());
			basicProfile.setAvgRating(labourer.getAvgRating());
			basicProfile.setCity(labourer.getCity());
			basicProfile.setUsername(labourer.getLoginDetails().getUserName());
			basicProfile.setFieldOfSpecialization(labourer.getFieldOfSpecialization());
			basicProfile.setGroupCount(labourer.getGroupCount());
			basicProfile.setPersonOrGroup(labourer.getPersonOrGroup());
			basicProfile.setImageUrl(labourer.getImageUrl());
			basicProfile.setNoOfServicesProvided(labourer.getNoOfServicesProvided());
			basicProfile.setWithUsSince(labourer.getWithUsSince());
			basicProfile.setName(labourer.getName());
			basicProfile.setState(labourer.getState());
			labourerBasicProfiles.add(basicProfile);
		}
		return labourerBasicProfiles;
	}

	@Override
	public ArrayList<LabourerBasicProfile> findLabourersByState(String state) {
		ArrayList<Labourer> labourers = labourersDAO.findByState(state);
		ArrayList<LabourerBasicProfile> labourerBasicProfiles = new ArrayList<>();
		for(Labourer labourer:labourers) {
			LabourerBasicProfile basicProfile = new LabourerBasicProfile();
			basicProfile.setAddress(labourer.getAddress());
			basicProfile.setAge(labourer.getAge());
			basicProfile.setGender(labourer.getGender());
			basicProfile.setAvgRating(labourer.getAvgRating());
			basicProfile.setCity(labourer.getCity());
			basicProfile.setYearsOfExperience(labourer.getYearsOfExperience());
			basicProfile.setUsername(labourer.getLoginDetails().getUserName());
			basicProfile.setFieldOfSpecialization(labourer.getFieldOfSpecialization());
			basicProfile.setGroupCount(labourer.getGroupCount());
			basicProfile.setPersonOrGroup(labourer.getPersonOrGroup());
			basicProfile.setImageUrl(labourer.getImageUrl());
			basicProfile.setNoOfServicesProvided(labourer.getNoOfServicesProvided());
			basicProfile.setWithUsSince(labourer.getWithUsSince());
			basicProfile.setName(labourer.getName());
			basicProfile.setState(labourer.getState());
			labourerBasicProfiles.add(basicProfile);
		}
		return labourerBasicProfiles;
	}

	@Override
	public ArrayList<LabourerBasicProfile> findLabourersByFieldAndCity(String field, String city) {
		ArrayList<Labourer> labourers = labourersDAO.findByFieldAndCity(field, city);
		ArrayList<LabourerBasicProfile> labourerBasicProfiles = new ArrayList<>();
		for(Labourer labourer:labourers) {
			LabourerBasicProfile basicProfile = new LabourerBasicProfile();
			basicProfile.setAddress(labourer.getAddress());
			basicProfile.setAge(labourer.getAge());
			basicProfile.setGender(labourer.getGender());
			basicProfile.setAvgRating(labourer.getAvgRating());
			basicProfile.setCity(labourer.getCity());
			basicProfile.setUsername(labourer.getLoginDetails().getUserName());
			basicProfile.setFieldOfSpecialization(labourer.getFieldOfSpecialization());
			basicProfile.setGroupCount(labourer.getGroupCount());
			basicProfile.setPersonOrGroup(labourer.getPersonOrGroup());
			basicProfile.setImageUrl(labourer.getImageUrl());
			basicProfile.setState(labourer.getState());
			basicProfile.setNoOfServicesProvided(labourer.getNoOfServicesProvided());
			basicProfile.setWithUsSince(labourer.getWithUsSince());
			basicProfile.setName(labourer.getName());
			labourerBasicProfiles.add(basicProfile);
		}
		return labourerBasicProfiles;
	}

	@Override
	public ArrayList<LabourerBasicProfile> findLabourersByFieldAndState(String field, String state) {
		ArrayList<Labourer> labourers = labourersDAO.findByFieldAndState(field, state);
		ArrayList<LabourerBasicProfile> labourerBasicProfiles = new ArrayList<>();
		for(Labourer labourer:labourers) {
			LabourerBasicProfile basicProfile = new LabourerBasicProfile();
			basicProfile.setAddress(labourer.getAddress());
			basicProfile.setAge(labourer.getAge());
			basicProfile.setGender(labourer.getGender());
			basicProfile.setAvgRating(labourer.getAvgRating());
			basicProfile.setCity(labourer.getCity());
			basicProfile.setUsername(labourer.getLoginDetails().getUserName());
			basicProfile.setFieldOfSpecialization(labourer.getFieldOfSpecialization());
			basicProfile.setGroupCount(labourer.getGroupCount());
			basicProfile.setPersonOrGroup(labourer.getPersonOrGroup());
			basicProfile.setImageUrl(labourer.getImageUrl());
			basicProfile.setState(labourer.getState());
			basicProfile.setNoOfServicesProvided(labourer.getNoOfServicesProvided());
			basicProfile.setWithUsSince(labourer.getWithUsSince());
			basicProfile.setName(labourer.getName());
			labourerBasicProfiles.add(basicProfile);
		}
		return labourerBasicProfiles;
	}

	@Override
	public ArrayList<WorkerApplicationProfile> findWAByLabourer(String labourerId) {
		LoginDetails loginDetails = loginDetailsDAO.findByUserName(labourerId);
		if(loginDetails!=null) {
			Labourer labourer = labourersDAO.findByLoginDetails(loginDetails);
			ArrayList<WorkerApplication> workerApplications = workerApplicationDAO.findByLabourer(labourer);
			ArrayList<WorkerApplicationProfile> workerApplicationProfiles = new ArrayList<>();
			for(WorkerApplication w : workerApplications) {
				WorkerApplicationProfile applicationProfile = new WorkerApplicationProfile();
				applicationProfile.setApproved(w.getApproved());
				applicationProfile.setContractorRequirementId(w.getContractorRequirementId().getContractorRequirementId());
				applicationProfile.setCreatedDate(w.getCreatedDate());
				applicationProfile.setDescription(w.getContractorRequirementId().getDescription());
				applicationProfile.setLabourerId(w.getLabourer().getLoginDetails().getUserName());
				applicationProfile.setWorkerApplicationId(w.getWorkerApplicationId());
				applicationProfile.setLabourerName(w.getLabourer().getName());
				workerApplicationProfiles.add(applicationProfile);
			}
			return workerApplicationProfiles;
		} else {
			return null;
		}
	}
	
	@Override
	public ArrayList<WorkerApplicationProfile> findWAByLabourerHistory(String labourerId) {
		LoginDetails loginDetails = loginDetailsDAO.findByUserName(labourerId);
		if(loginDetails!=null) {
			Labourer labourer = labourersDAO.findByLoginDetails(loginDetails);
			ArrayList<WorkerApplication> workerApplications = workerApplicationDAO.findByLabourer(labourer);
			ArrayList<WorkerApplicationProfile> workerApplicationProfiles = new ArrayList<>();
			for(WorkerApplication w : workerApplications) {
				WorkerApplicationProfile applicationProfile = new WorkerApplicationProfile();
				applicationProfile.setApproved(w.getApproved());
				applicationProfile.setContractorRequirementId(w.getContractorRequirementId().getContractorRequirementId());
				applicationProfile.setCreatedDate(w.getCreatedDate());
				applicationProfile.setDescription(w.getContractorRequirementId().getDescription());
				applicationProfile.setLabourerId(w.getLabourer().getLoginDetails().getUserName());
				applicationProfile.setWorkerApplicationId(w.getWorkerApplicationId());
				applicationProfile.setLabourerName(w.getLabourer().getName());
				applicationProfile.setContractorName(w.getContractorRequirementId().getContractorOb().getName());
				applicationProfile.setSiteCity(w.getContractorRequirementId().getSiteCity());
				applicationProfile.setSiteState(w.getContractorRequirementId().getSiteState());
				applicationProfile.setSiteAddress(w.getContractorRequirementId().getSiteAddress());
				workerApplicationProfiles.add(applicationProfile);
			}
			return workerApplicationProfiles;
		} else {
			return null;
		}
	}
	@Override
	public ArrayList<WorkerApplicationProfile> findWAByContractor(String contractorUsername) {
		LoginDetails loginDetails = loginDetailsDAO.findByUserName(contractorUsername);
		Contractor contractor = contractorsDAO.findByLoginDetails(loginDetails);
		ArrayList<ContractorRequirement> arrayList= contractorRequirementDAO.findByContractor(contractor);
		ArrayList<WorkerApplication> workerApplications = new ArrayList<WorkerApplication>();
		for(ContractorRequirement contractorRequirement: arrayList) {
			ArrayList<WorkerApplication> workerApplications1 = workerApplicationDAO.findByContractorRequirementId(contractorRequirement);
			workerApplications.addAll(workerApplications1);
		}
		ArrayList<WorkerApplicationProfile> workerApplicationProfiles = new ArrayList<>();
		for(WorkerApplication w : workerApplications) {
			WorkerApplicationProfile applicationProfile = new WorkerApplicationProfile();
			applicationProfile.setApproved(w.getApproved());
			applicationProfile.setContractorRequirementId(w.getContractorRequirementId().getContractorRequirementId());
			applicationProfile.setCreatedDate(w.getCreatedDate());
			applicationProfile.setDescription(w.getContractorRequirementId().getDescription());
			applicationProfile.setLabourerId(w.getLabourer().getLoginDetails().getUserName());
			applicationProfile.setWorkerApplicationId(w.getWorkerApplicationId());
			applicationProfile.setLabourerName(w.getLabourer().getName());
			workerApplicationProfiles.add(applicationProfile);
		}
		return workerApplicationProfiles;
	}

	@Override
	public ArrayList<WorkerApplication> findWAByContractorRequirementId(int contractorRequirementId) {
		ContractorRequirement contractorRequirement = contractorRequirementDAO.findByContractorRequirementId(contractorRequirementId);
		if (contractorRequirement!=null) {
			ArrayList<WorkerApplication> workerApplications = workerApplicationDAO.findByContractorRequirementId(contractorRequirement);
			return workerApplications;
		}else {
			return null;
		}
		
	}

	@Override
	public ArrayList<ContractorRequirement> findCRByContractor(String username) {
		LoginDetails loginDetails = loginDetailsDAO.findByUserName(username);
		if(loginDetails!=null) {
			Contractor contractor = contractorsDAO.findByLoginDetails(loginDetails);
			ArrayList<ContractorRequirement> contractorRequirements = contractorRequirementDAO.findByContractor(contractor);
			return contractorRequirements;
		} else {
			return null;
		}
	}

	@Override
	public ArrayList<ContractorRequirement> findCRBySiteCity(String city) {
		ArrayList<ContractorRequirement> contractorRequirements = contractorRequirementDAO.findCRBySiteCity(city);
		return contractorRequirements;
	}

	@Override
	public ArrayList<ContractorRequirementProfile> findCRByField(String field) {
		ArrayList<ContractorRequirementProfile> contractorRequirementProfiles = new ArrayList<>();
		String[] fields = field.split(",");;
		for(String f: fields) {
			ArrayList<ContractorRequirement> contractorRequirements = contractorRequirementDAO.findCRByField(f);
			for(ContractorRequirement contractorRequirement: contractorRequirements) {
				ContractorRequirementProfile contractorRequirementProfile = new ContractorRequirementProfile();
				contractorRequirementProfile.setContractorName(contractorRequirement.getContractorOb().getName());
				contractorRequirementProfile.setContractorRequirementId(contractorRequirement.getContractorRequirementId());
				contractorRequirementProfile.setField(contractorRequirement.getField());
				contractorRequirementProfile.setIsActive(contractorRequirement.getIsActive());
				contractorRequirementProfile.setNoOfPeople(contractorRequirement.getNoOfPeople());
				contractorRequirementProfile.setNoOfPeopleApplied(contractorRequirement.getNoOfPeopleApplied());
				contractorRequirementProfile.setSiteAddress(contractorRequirement.getSiteAddress());
				contractorRequirementProfile.setSiteCity(contractorRequirement.getSiteCity());
				contractorRequirementProfile.setSiteState(contractorRequirement.getSiteState());
				contractorRequirementProfile.setCreatedDate(contractorRequirement.getCreatedDate());
				contractorRequirementProfile.setButtonText("Apply");
				contractorRequirementProfiles.add(contractorRequirementProfile);
			}
		}
		return contractorRequirementProfiles;
	}

	@Override
	public ArrayList<ContractorRequirementProfile> findCRByFieldAndSiteCity(String field, String city) {
		ArrayList<ContractorRequirementProfile> contractorRequirementProfiles = new ArrayList<>();
		String[] fields = field.split(",");
		for(String f: fields) {
			System.out.println(f);
			ArrayList<ContractorRequirement> contractorRequirements = contractorRequirementDAO.findByFieldAndSiteCity(f,city);
			for(ContractorRequirement contractorRequirement: contractorRequirements) {
				ContractorRequirementProfile contractorRequirementProfile = new ContractorRequirementProfile();
				contractorRequirementProfile.setContractorName(contractorRequirement.getContractorOb().getName());
				contractorRequirementProfile.setContractorRequirementId(contractorRequirement.getContractorRequirementId());
				contractorRequirementProfile.setField(contractorRequirement.getField());
				contractorRequirementProfile.setIsActive(contractorRequirement.getIsActive());
				contractorRequirementProfile.setNoOfPeople(contractorRequirement.getNoOfPeople());
				contractorRequirementProfile.setNoOfPeopleApplied(contractorRequirement.getNoOfPeopleApplied());
				contractorRequirementProfile.setSiteAddress(contractorRequirement.getSiteAddress());
				contractorRequirementProfile.setSiteCity(contractorRequirement.getSiteCity());
				contractorRequirementProfile.setSiteState(contractorRequirement.getSiteState());
				contractorRequirementProfile.setCreatedDate(contractorRequirement.getCreatedDate());
				contractorRequirementProfile.setButtonText("Apply");
				contractorRequirementProfiles.add(contractorRequirementProfile);
			}
		}
		return contractorRequirementProfiles;
	}

	@Override
	public ArrayList<ContractorRequirementProfile> findCRByFieldAndSiteState(String field, String state) {
		ArrayList<ContractorRequirementProfile> contractorRequirementProfiles = new ArrayList<>();
		String[] fields = field.split(",");
		for(String f: fields) {
			ArrayList<ContractorRequirement> contractorRequirements = contractorRequirementDAO.findByFieldAndSiteState(f,state);
			for(ContractorRequirement contractorRequirement: contractorRequirements) {
				ContractorRequirementProfile contractorRequirementProfile = new ContractorRequirementProfile();
				contractorRequirementProfile.setContractorName(contractorRequirement.getContractorOb().getName());
				contractorRequirementProfile.setContractorRequirementId(contractorRequirement.getContractorRequirementId());
				contractorRequirementProfile.setField(contractorRequirement.getField());
				contractorRequirementProfile.setIsActive(contractorRequirement.getIsActive());
				contractorRequirementProfile.setNoOfPeople(contractorRequirement.getNoOfPeople());
				contractorRequirementProfile.setNoOfPeopleApplied(contractorRequirement.getNoOfPeopleApplied());
				contractorRequirementProfile.setSiteAddress(contractorRequirement.getSiteAddress());
				contractorRequirementProfile.setSiteCity(contractorRequirement.getSiteCity());
				contractorRequirementProfile.setCreatedDate(contractorRequirement.getCreatedDate());
				contractorRequirementProfile.setSiteState(contractorRequirement.getSiteState());
				contractorRequirementProfile.setButtonText("Apply");
				contractorRequirementProfiles.add(contractorRequirementProfile);
			}
		}
		return contractorRequirementProfiles;
	}

	@Override
	public ArrayList<ContractorRequirement> findActiveCRByContractor(String contractorId) {
		LoginDetails loginDetails = loginDetailsDAO.findByUserName(contractorId);
		if(loginDetails!=null) {
			Contractor contractor = contractorsDAO.findByLoginDetails(loginDetails);
			ArrayList<ContractorRequirement> contractorRequirements = contractorRequirementDAO.findByContractorActive(contractor);
			return contractorRequirements;
		} else {
			return null;
		}
	}

	@Override
	public ArrayList<ContractorRequirement> findInActiveCRByContractor(String contractorId) {
		LoginDetails loginDetails = loginDetailsDAO.findByUserName(contractorId);
		if(loginDetails!=null) {
			Contractor contractor = contractorsDAO.findByLoginDetails(loginDetails);
			ArrayList<ContractorRequirement> contractorRequirements = contractorRequirementDAO.findByContractorInActive(contractor);
			return contractorRequirements;
		} else {
			return null;
		}
	}

	@Override
	public ArrayList<ContractorRequirementProfile> findCRForLabourerHome(String siteCity, String siteState, String field,String lUserName) {
		ArrayList<ContractorRequirementProfile> contractorRequirementProfiles = new ArrayList<>();
		String[] fields = field.split(",");
		LoginDetails loginDetails = loginDetailsDAO.findByUserName(lUserName);
		if(loginDetails!=null) {
		for(String f: fields) {
			ArrayList<ContractorRequirement> contractorRequirements = contractorRequirementDAO.findCRForLabourerHome(siteCity, siteState, f);
			Labourer labourer = labourersDAO.findByLoginDetails(loginDetails);
			ArrayList<Integer> workerApplications = workerApplicationDAO.findContractorRequirementIdsByLabourer(labourer);
			
			
			for(ContractorRequirement contractorRequirement: contractorRequirements) {
				if(!workerApplications.contains(contractorRequirement.getContractorRequirementId())) {
					ContractorRequirementProfile contractorRequirementProfile = new ContractorRequirementProfile();
					contractorRequirementProfile.setContractorName(contractorRequirement.getContractorOb().getName());
					contractorRequirementProfile.setContractorRequirementId(contractorRequirement.getContractorRequirementId());
					contractorRequirementProfile.setField(contractorRequirement.getField());
					contractorRequirementProfile.setIsActive(contractorRequirement.getIsActive());
					contractorRequirementProfile.setNoOfPeople(contractorRequirement.getNoOfPeople());
					contractorRequirementProfile.setNoOfPeopleApplied(contractorRequirement.getNoOfPeopleApplied());
					contractorRequirementProfile.setSiteAddress(contractorRequirement.getSiteAddress());
					contractorRequirementProfile.setSiteCity(contractorRequirement.getSiteCity());
					contractorRequirementProfile.setCreatedDate(contractorRequirement.getCreatedDate());
					contractorRequirementProfile.setSiteState(contractorRequirement.getSiteState());
					contractorRequirementProfile.setButtonText("Apply");
					contractorRequirementProfiles.add(contractorRequirementProfile);
				}
			}
		}
		return contractorRequirementProfiles;
		}else {
			return null;
		}
	}

}
