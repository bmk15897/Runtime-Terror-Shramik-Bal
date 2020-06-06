package com.example.demo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="contractor")
public class Contractor {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="contractor_id")
	private int contractorId;
	
	@JsonBackReference(value="contractor-loginDetails")
	@OneToOne
	@JoinColumn(name = "user_id")
	private LoginDetails loginDetails;
	
	@Column(name="name")
	private String name;
		
	@Column(name="aadhaar_id", unique = true, nullable = true)
	private String aadhaarId;
	
	@Column(name="contact_no", unique = true, nullable = false)
	private String contactNo;
	
	@Column(name="no_of_services_used")
	private int noOfServicesUsed;
	
	@Column(name="avg_rating")
	private float avgRating;
	
	@Column(name="with_us_since")
	private int withUsSince;
	
	@Column(name="image_url")
	private String imageUrl;
	
	@Column(name="address")
	private String address;
	
	@Column(name="age")
	private int age;
	
	@Column(name="date_of_birth")
	private String dateOfBirth;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="city")
	private String city;
	
	@Column(name="state")
	private String state;
	
	@JsonIgnore
//	@JsonManagedReference(value="contractor-contractor-requirement")
	@OneToMany(mappedBy="contractorOb",cascade=CascadeType.ALL)
	Set<ContractorRequirement> workerRequirement = new HashSet<ContractorRequirement>();
}
