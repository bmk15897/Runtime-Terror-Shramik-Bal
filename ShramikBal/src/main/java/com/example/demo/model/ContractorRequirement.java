package com.example.demo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="contractor_requirement")
public class ContractorRequirement {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="contractor_requirement_id")
	private int contractorRequirementId;
	
	@JsonBackReference(value="contractor-contractor-requirement")
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="contractor_id")
	private Contractor contractorOb;
	
	@Column(name="description")
	private String description;
	
	@Column(name="field")
	private String field;
	
	@Column(name="no_of_people")
	private int noOfPeople;
	
	@Column(name="no_of_people_applied")
	private int noOfPeopleApplied;
	
	@Column(name="site_address")
	private String siteAddress;
	
	@Column(name="site_city")
	private String siteCity;
	
	@Column(name="site_state")
	private String siteState;
	
	@Column(name="is_active")
	private String isActive;
	
	@Column(name="created_date")
	private String createdDate;
	
	@JsonIgnore
//	@JsonManagedReference(value="contractor-requirement-worker-application")
	@OneToMany(mappedBy="contractorRequirementId",cascade=CascadeType.ALL)
	Set<WorkerApplication> workerApp = new HashSet<WorkerApplication>();
}
