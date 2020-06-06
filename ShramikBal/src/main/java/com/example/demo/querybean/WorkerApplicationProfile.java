package com.example.demo.querybean;

import lombok.Data;

@Data
public class WorkerApplicationProfile {
	private int workerApplicationId;
	private String labourerId;
	private String labourerName; 
	private int contractorRequirementId;
	private String description;
	private String createdDate;
	private String approved;
	private String contractorName;
	private String siteAddress;
	private String siteCity;
	private String siteState;
}
