package com.example.demo.querybean;

import lombok.Data;

@Data
public class ContractorRequirementRequest {
	private int contractorRequirementId;
	private String contractorUserName;
	private String description;
	private String field;
	private int noOfPeople;
	private int noOfPeopleApplied;
	private String siteAddress;
	private String siteCity;
	private String siteState;
	private String isActive;
	private String createdDate;
}
