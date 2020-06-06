package com.example.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
@Table(name="worker_application")
public class WorkerApplication {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="worker_application_id")
	private int workerApplicationId;
	
	@JsonBackReference(value="labourer-worker-application")
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="labourer_id")
	private Labourer labourer;
	
	@JsonBackReference(value="contractor-requirement-worker-application")
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="contractor_requirement_id")
	private ContractorRequirement contractorRequirementId;
	
	@Column(name="created_date")
	private String createdDate;
	
	@Column(name="approved")
	private String approved;
}
