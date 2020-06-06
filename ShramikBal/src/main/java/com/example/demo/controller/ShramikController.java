package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Contractor;
import com.example.demo.model.ContractorRequirement;
import com.example.demo.model.Labourer;
import com.example.demo.model.WorkerApplication;
import com.example.demo.querybean.ContractorRequirementRequest;
import com.example.demo.querybean.CustomResponseEntity;
import com.example.demo.querybean.UserLoginBean;
import com.example.demo.querybean.UserProfileBean;
import com.example.demo.querybean.WorkerApplicationProfile;
import com.example.demo.service.LoginService;
import com.example.demo.service.RequirementService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/shramik-bal")
public class ShramikController {

	@Autowired
	LoginService loginService;
	
	@Autowired
	RequirementService requirementService;

	@PostMapping("/signin")
	public ResponseEntity<CustomResponseEntity> shramikLogin(@RequestBody UserLoginBean userLoginBean) {
		log.info("Post Request Url - signin");
		UserProfileBean user = loginService.findUserByUserName(userLoginBean.getUserName());
		CustomResponseEntity customResponseEntity = new CustomResponseEntity();
		if (user != null) {
			if (userLoginBean.getPassword().equals(user.getLoginDetails().getPassword())) {
				customResponseEntity.setObject(user);
				customResponseEntity.setMessage("Successful sign-in");
				return new ResponseEntity<>(customResponseEntity, HttpStatus.OK);
			}
			customResponseEntity.setMessage("Password not found");
			return new ResponseEntity<>(null, HttpStatus.OK);
		} else {
			customResponseEntity.setMessage("Username not found");
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
	}

	@PostMapping("/signup") 
	public ResponseEntity<CustomResponseEntity> shramikSignup(@RequestBody UserProfileBean userProfileBean) {
		log.info("Post Request Url - /signup");
		CustomResponseEntity customResponseEntity = new CustomResponseEntity();
		if(loginService.findUserByUserName(userProfileBean.getLoginDetails().getUserName())!=null) {
			customResponseEntity.setMessage("Username already exists");
			return new ResponseEntity<>(null,HttpStatus.OK);
		} else {
			customResponseEntity.setObject(loginService.saveUser(userProfileBean));
			customResponseEntity.setMessage("Successfully registered");
			return new ResponseEntity<>(customResponseEntity, HttpStatus.OK);
		}
	}

	@PostMapping("/update-profile-fields")
	public ResponseEntity<CustomResponseEntity> updateProfileFields(@RequestBody UserProfileBean userProfileBean) {
		log.info("Post Request Url - /update-profile-fields");
		CustomResponseEntity customResponseEntity = new CustomResponseEntity();
		customResponseEntity.setMessage("Profile updated");
		customResponseEntity.setObject(requirementService.updateProfileFields(userProfileBean));
		return new ResponseEntity<>(customResponseEntity, HttpStatus.OK);
	}

	@PostMapping("/update-profile-password")
	public ResponseEntity<CustomResponseEntity> updateProfilePassword(@RequestBody Map<String, String> values) {
		log.info("Post Request Url - /update-profile-password");
		CustomResponseEntity customResponseEntity = new CustomResponseEntity();
		customResponseEntity.setMessage("Profile password updated");
		customResponseEntity.setObject(requirementService.updateProfilePassword(values));
		return new ResponseEntity<>(customResponseEntity, HttpStatus.OK);
	}

	@PostMapping("/get-labourer-details")
	public ResponseEntity<CustomResponseEntity> getLabourerDetails(@RequestBody String username) {
		log.info("Post Request Url - /get-labourer-details");
		Labourer labourer = requirementService.findLabourerById(username);
		
		CustomResponseEntity customResponseEntity = new CustomResponseEntity();
		if (labourer != null) {
			labourer.setLoginDetails(null);
			customResponseEntity.setMessage("Labourer details fetched");
			customResponseEntity.setObject(labourer);
			return new ResponseEntity<>(customResponseEntity, HttpStatus.OK);
		} else {
			customResponseEntity.setMessage("Labourer details not found");
			customResponseEntity.setObject(labourer);
			return new ResponseEntity<>(customResponseEntity, HttpStatus.OK);
		}
	}
	
	@PostMapping("/get-contractor-details")
	public ResponseEntity<CustomResponseEntity> getContractorDetails(@RequestBody String username) {
		log.info("Post Request Url - /get-contractor-details");
		Contractor contractor = requirementService.findContractorById(username);
		CustomResponseEntity customResponseEntity = new CustomResponseEntity();
		if (contractor != null) {
			customResponseEntity.setMessage("Contractor details fetched");
			customResponseEntity.setObject(contractor);
			return new ResponseEntity<>(customResponseEntity, HttpStatus.OK);
		} else {
			customResponseEntity.setMessage("Contractor details not found");
			customResponseEntity.setObject(contractor);
			return new ResponseEntity<>(customResponseEntity, HttpStatus.OK);
		}
	}

	@PostMapping("/post-contractor-requirement")
	public ResponseEntity<CustomResponseEntity> addContractorRequirement(@RequestBody ContractorRequirementRequest contractorRequirement) {
		log.info("Post Request Url - /post-contractor-requirement");
		CustomResponseEntity customResponseEntity = new CustomResponseEntity();
		customResponseEntity.setMessage("Contractor Requirement application added");
		customResponseEntity.setObject(requirementService.addContractorRequirement(contractorRequirement));
		return new ResponseEntity<>(customResponseEntity, HttpStatus.OK);
	}

	@PostMapping("/post-worker-application")
	public ResponseEntity<CustomResponseEntity> addWorkerApplication(@RequestBody Map<String, String> values) {
		log.info("Post Request Url - /post-worker-application");
		CustomResponseEntity customResponseEntity = new CustomResponseEntity();
		customResponseEntity.setMessage("Worker Application added");
		customResponseEntity.setObject(requirementService.addWorkerApplication(Integer.parseInt(values.get("contractorRequirementId")), values.get("labourer"), values.get("createdDate")));
		return new ResponseEntity<>(customResponseEntity,HttpStatus.OK);
	}
	
	@PostMapping("/approve-worker-application")
	public ResponseEntity<CustomResponseEntity> approveWorkerApplication(@RequestBody int workerApplication) {
		log.info("Post Request Url - /approve-worker-application");
		CustomResponseEntity customResponseEntity = new CustomResponseEntity();
		customResponseEntity.setMessage("Worker Application approved");
		customResponseEntity.setObject(requirementService.approveWorkerApplication(workerApplication));
		return new ResponseEntity<>(customResponseEntity,HttpStatus.OK);
	}
	
	@PostMapping("/decline-worker-application")
	public ResponseEntity<CustomResponseEntity> declineWorkerApplication(@RequestBody int workerApplication) {
		log.info("Post Request Url - /decline-worker-application");
		CustomResponseEntity customResponseEntity = new CustomResponseEntity();
		customResponseEntity.setMessage("Worker Application declined");
		customResponseEntity.setObject(requirementService.declineWorkerApplication(workerApplication));
		return new ResponseEntity<>(customResponseEntity,HttpStatus.OK);
	}
	

	@PostMapping("/get-labourers-by-field")
	public ResponseEntity<CustomResponseEntity> findLabourersByField(@RequestBody String field) {
		log.info("Post Request Url - /get-labourers-by-field");
		CustomResponseEntity customResponseEntity = new CustomResponseEntity();
		customResponseEntity.setMessage("Labourer details fetched");
		customResponseEntity.setObject(requirementService.findLabourersByField(field));
		return new ResponseEntity<>(customResponseEntity, HttpStatus.OK);
	}
	
	@PostMapping("/get-labourers-by-city")
	public ResponseEntity<CustomResponseEntity> findLabourersByCity(@RequestBody String city) {
		log.info("Post Request Url - /get-labourers-by-city");
		CustomResponseEntity customResponseEntity = new CustomResponseEntity();
		customResponseEntity.setMessage("Labourer details fetched");
		customResponseEntity.setObject(requirementService.findLabourersByCity(city));
		return new ResponseEntity<>(customResponseEntity, HttpStatus.OK);
	}

	@PostMapping("/get-labourers-by-state")
	public ResponseEntity<CustomResponseEntity> findLabourersByState(@RequestBody String state) {
		log.info("Post Request Url - /get-labourers-by-state");
		CustomResponseEntity customResponseEntity = new CustomResponseEntity();
		customResponseEntity.setMessage("Labourer details fetched");
		customResponseEntity.setObject(requirementService.findLabourersByState(state));
		return new ResponseEntity<>(customResponseEntity, HttpStatus.OK);
	}
	
	@PostMapping("/get-labourers-by-field-and-city")
	public ResponseEntity<CustomResponseEntity> findLabourersByFieldAndCity(@RequestBody Map<String, String> values) {
		log.info("Post Request Url - /get-labourers-by-field-and-city");
		CustomResponseEntity customResponseEntity = new CustomResponseEntity();
		customResponseEntity.setMessage("Labourer details fetched");
		customResponseEntity.setObject(requirementService.findLabourersByFieldAndCity(values.get("field"), values.get("city")));
		return new ResponseEntity<>(customResponseEntity, HttpStatus.OK);
	}
	
	@PostMapping("/get-labourers-by-field-and-state")
	public ResponseEntity<CustomResponseEntity> findLabourersByFieldAndState(@RequestBody Map<String, String> values) {
		log.info("Post Request Url - /get-labourers-by-field-and-state");
		CustomResponseEntity customResponseEntity = new CustomResponseEntity();
		customResponseEntity.setMessage("Labourer details fetched");
		customResponseEntity.setObject(requirementService.findLabourersByFieldAndState(values.get("field"), values.get("state")));
		return new ResponseEntity<>(customResponseEntity, HttpStatus.OK);
	}

	@PostMapping("/get-WA-by-labourer")
	public ResponseEntity<CustomResponseEntity> findWorkerApplicationsByLabourer(@RequestBody String labourer) {
		log.info("Post Request Url - /get-WA-by-labourer");
		CustomResponseEntity customResponseEntity = new CustomResponseEntity();
		customResponseEntity.setMessage("Worker Applications fetched");
		customResponseEntity.setObject(requirementService.findWAByLabourer(labourer));
		return new ResponseEntity<>(customResponseEntity, HttpStatus.OK);
	}
	
	@PostMapping("/get-WA-by-labourer-history")
	public ResponseEntity<CustomResponseEntity> findWorkerApplicationsByLabourerHistory(@RequestBody String labourer) {
		log.info("Post Request Url - /get-WA-by-labourer-history");
		CustomResponseEntity customResponseEntity = new CustomResponseEntity();
		customResponseEntity.setMessage("Worker Applications fetched");
		customResponseEntity.setObject(requirementService.findWAByLabourerHistory(labourer));
		return new ResponseEntity<>(customResponseEntity, HttpStatus.OK);
	}

	@PostMapping("/get-WA-by-contractor-requirement")
	public ResponseEntity<CustomResponseEntity> findWorkerApplicationsByContractorRequirement(@RequestBody int contractorRequirement) {
		log.info("Post Request Url - /get-WA-by-contractor-requirement");
		ArrayList<WorkerApplication> workerApplications = requirementService.findWAByContractorRequirementId(contractorRequirement);
		
		CustomResponseEntity customResponseEntity = new CustomResponseEntity();
		
		if(workerApplications!=null) {
			customResponseEntity.setMessage("Worker Applications fetched");
			customResponseEntity.setObject(workerApplications);
			return new ResponseEntity<>(customResponseEntity, HttpStatus.OK);
		}
		else {
			customResponseEntity.setMessage("Worker Applications not found");
			customResponseEntity.setObject(workerApplications);
			return new ResponseEntity<>(customResponseEntity, HttpStatus.OK);
		}
	}

	@PostMapping("/get-WA-by-contractor")
	public ResponseEntity<CustomResponseEntity> findWorkerApplicationsByContractor(@RequestBody String username) {
		log.info("Post Request Url - /get-WA-by-contractor");
		ArrayList<WorkerApplicationProfile> workerApplications = requirementService.findWAByContractor(username);
		CustomResponseEntity customResponseEntity = new CustomResponseEntity();
		if(workerApplications!=null) {
			customResponseEntity.setMessage("Worker Applications fetched");
			customResponseEntity.setObject(workerApplications);
			return new ResponseEntity<>(customResponseEntity, HttpStatus.OK);
		}
		else {
			customResponseEntity.setMessage("Worker Applications not found");
			customResponseEntity.setObject(workerApplications);
			return new ResponseEntity<>(customResponseEntity, HttpStatus.OK);
		}
	}

	@PostMapping("/get-CR-by-contractor")
	public ResponseEntity<CustomResponseEntity> findContractorRequirementsByContractor(@RequestBody String contractor) {
		log.info("Post Request Url - /get-CR-by-contractor");
		ArrayList<ContractorRequirement> contractorRequirements = requirementService.findCRByContractor(contractor);
		CustomResponseEntity customResponseEntity = new CustomResponseEntity();
		if(contractorRequirements!=null) {
			customResponseEntity.setMessage("Contractor Requirements fetched");
			customResponseEntity.setObject(contractorRequirements);
			return new ResponseEntity<>(customResponseEntity, HttpStatus.OK);
		} else {
			customResponseEntity.setMessage("Contractor Requirements not found");
			customResponseEntity.setObject(contractorRequirements);
			return new ResponseEntity<>(customResponseEntity, HttpStatus.OK);
		}
	}
	
	@PostMapping("/get-CR-by-site-city")
	public ResponseEntity<CustomResponseEntity> findContractorRequirementsBySiteCity(@RequestBody String siteCity) {
		log.info("Post Request Url - /get-CR-by-site-city");
		CustomResponseEntity customResponseEntity = new CustomResponseEntity();
		customResponseEntity.setMessage("Contractor Requirements fetched");
		customResponseEntity.setObject(requirementService.findCRBySiteCity(siteCity));
		return new ResponseEntity<>(customResponseEntity, HttpStatus.OK);
	}
	
	@PostMapping("/get-CR-by-field")
	public ResponseEntity<CustomResponseEntity> findContractorRequirementsByField(@RequestBody String field) {
		log.info("Post Request Url - /get-CR-by-field");
		CustomResponseEntity customResponseEntity = new CustomResponseEntity();
		customResponseEntity.setMessage("Contractor Requirements fetched");
		customResponseEntity.setObject(requirementService.findCRByField(field));
		return new ResponseEntity<>(customResponseEntity, HttpStatus.OK);
	}
	
	@PostMapping("/get-CR-by-site-city-and-specialization")
	public ResponseEntity<CustomResponseEntity> findContractorRequirementsBySiteCityAndFieldOfSpecialization(@RequestBody Map<String, String> values) {
		log.info("Post Request Url - /get-CR-by-site-city-and-specialization");
		CustomResponseEntity customResponseEntity = new CustomResponseEntity();
		customResponseEntity.setMessage("Contractor Requirements fetched");
		customResponseEntity.setObject(requirementService.findCRByFieldAndSiteCity(values.get("field"), values.get("city")));
		return new ResponseEntity<>(customResponseEntity, HttpStatus.OK);
	}
	
	@PostMapping("/get-CR-by-site-state-and-specialization")
	public ResponseEntity<CustomResponseEntity> findContractorRequirementsBySiteStateAndFieldOfSpecialization(@RequestBody Map<String, String> values) {
		log.info("Post Request Url - /get-CR-by-site-state-and-specialization");
		CustomResponseEntity customResponseEntity = new CustomResponseEntity();
		customResponseEntity.setMessage("Contractor Requirements fetched");
		customResponseEntity.setObject(requirementService.findCRByFieldAndSiteState(values.get("field"), values.get("state")));
		return new ResponseEntity<>(customResponseEntity, HttpStatus.OK);
	}

	@PostMapping("/get-active-CR-by-contractor")
	public ResponseEntity<CustomResponseEntity> findActiveContractorRequirementsByContractor(@RequestBody String contractor) {
		log.info("Post Request Url - /get-active-CR-by-contractor");
		ArrayList<ContractorRequirement> contractorRequirements = requirementService.findActiveCRByContractor(contractor);
		CustomResponseEntity customResponseEntity = new CustomResponseEntity();
		if(contractorRequirements!=null) {
			customResponseEntity.setMessage("Active Contractor Requirements fetched");
			customResponseEntity.setObject(contractorRequirements);
			return new ResponseEntity<>(customResponseEntity, HttpStatus.OK);
		} else {
			customResponseEntity.setMessage("Active Contractor Requirements not found");
			customResponseEntity.setObject(contractorRequirements);
			return new ResponseEntity<>(customResponseEntity, HttpStatus.OK);
		}
	}
	
	@PostMapping("/get-inactive-CR-by-contractor")
	public ResponseEntity<CustomResponseEntity> findInActiveContractorRequirementsByContractor(@RequestBody String contractor) {
		log.info("Post Request Url - /get-inactive-CR-by-contractor");
		ArrayList<ContractorRequirement> contractorRequirements = requirementService.findInActiveCRByContractor(contractor);
		CustomResponseEntity customResponseEntity = new CustomResponseEntity();
		if(contractorRequirements!=null) {
			customResponseEntity.setMessage("Inactive Contractor Requirements fetched");
			customResponseEntity.setObject(contractorRequirements);
			return new ResponseEntity<>(customResponseEntity, HttpStatus.OK);
		} else {
			customResponseEntity.setMessage("Inactive Contractor Requirements not found");
			customResponseEntity.setObject(contractorRequirements);
			return new ResponseEntity<>(customResponseEntity, HttpStatus.OK);
		}
	}
	
}
