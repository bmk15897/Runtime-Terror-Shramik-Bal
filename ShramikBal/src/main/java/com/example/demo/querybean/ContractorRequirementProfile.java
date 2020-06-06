package com.example.demo.querybean;

import lombok.Data;

@Data
public class ContractorRequirementProfile {
	private int contractorRequirementId;
	private String contractorName;
	private String description;
	private String field;
	private int noOfPeople;
	private int noOfPeopleApplied;
	private String siteAddress;
	private String siteCity;
	private String siteState;
	private String isActive;
	private String createdDate;
	private String buttonText;
}
