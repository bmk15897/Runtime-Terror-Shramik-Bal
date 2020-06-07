package com.example.demo.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.Year;
import java.util.regex.Pattern;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ContractorsDAO;
import com.example.demo.dao.LabourersDAO;
import com.example.demo.dao.LoginDetailsDAO;
import com.example.demo.model.Contractor;
import com.example.demo.model.Labourer;
import com.example.demo.model.LoginDetails;
import com.example.demo.querybean.UserProfileBean;
import com.example.demo.supportAlgorithms.PwdHasher;
import com.example.demo.supportAlgorithms.VerhoeffAlgorithm;

@Service
public class LoginService implements LoginServiceInterface{

	@Autowired
	LabourersDAO labourersDAO;
	
	@Autowired
	ContractorsDAO contractorsDAO;
	
	@Autowired
	LoginDetailsDAO loginDetailsDAO;
	
	String TypeLabourer="L";
	String TypeContractor="C";
	
	public static boolean validateAadharNumber(String aadharNumber){
        Pattern aadharPattern = Pattern.compile("\\d{12}");
        boolean isValidAadhar = aadharPattern.matcher(aadharNumber).matches();
        if(isValidAadhar){
            isValidAadhar = VerhoeffAlgorithm.validateVerhoeff(aadharNumber);
        }
        return isValidAadhar;
    }
	
	@Override
	public UserProfileBean findUserByUserName(String userName) {
		
		LoginDetails details = loginDetailsDAO.findByUserName(userName);
		UserProfileBean userProfileBean = new UserProfileBean();
		if(details==null) {
			return null;
		} else {
			userProfileBean.setLoginDetails(details);
			if(details.getType().equals(TypeLabourer)) {
				userProfileBean.setLabourer(labourersDAO.findByLoginDetails(details));
			}else {
				userProfileBean.setContractor(contractorsDAO.findByLoginDetails(details));
			}
			return userProfileBean;
		}
	}

	@Override
	public UserProfileBean saveUser(UserProfileBean userProfileBean) throws NoSuchAlgorithmException, InvalidKeySpecException {
		String newPwd = PwdHasher.generateStorngPasswordHash(userProfileBean.getLoginDetails().getPassword());
		LoginDetails ld = userProfileBean.getLoginDetails();
		ld.setPassword(newPwd);
		LoginDetails loginDetails = loginDetailsDAO.saveLoginDetails(ld);
		if(userProfileBean.getLoginDetails().getType().equals(TypeLabourer)) {
			Labourer labourer = userProfileBean.getLabourer();
			labourer.setLoginDetails(loginDetails);
			labourer.setAvgRating(0);
			labourer.setNoOfServicesProvided(0);
			labourer.setWithUsSince(Year.now().getValue());
			labourersDAO.saveLabourer(labourer);
			userProfileBean.setLoginDetails(loginDetails);
			userProfileBean.setLabourer(labourer);
			return userProfileBean;
		}else {
			Contractor contractor = userProfileBean.getContractor();
			contractor.setLoginDetails(loginDetails);
			contractor.setAvgRating(0);
			contractor.setNoOfServicesUsed(0);
			contractor.setWithUsSince(Year.now().getValue());
			contractorsDAO.saveContractor(contractor);
			userProfileBean.setLoginDetails(loginDetails);
			userProfileBean.setContractor(contractor);
			return userProfileBean;
		}
	}
	
	

}
