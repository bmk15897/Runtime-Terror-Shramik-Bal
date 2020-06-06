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
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Entity
@Table(name="labourer")
public class Labourer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="labourer_id")
	private int labourerId;
	
	@JsonBackReference(value="labourer-loginDetails")
	@OneToOne
	@JoinColumn(name = "user_id")
	private LoginDetails loginDetails;
	
	@Column(name="name")
	private String name;
		
	@Column(name="aadhaar_id", unique = true, nullable = true)
	private String aadhaarId;
	
	@Column(name="years_of_experience")
	private int yearsOfExperience;
	
	@Column(name="age")
	private int age;
	
	@Column(name="address")
	private String address;
	
	@Column(name="city")
	private String city;
	
	@Column(name="state")
	private String state;
	
	@Column(name="field_of_specialization")
	private String fieldOfSpecialization;
	
	@Column(name="avg_rating")
	private float avgRating;
	
	@Column(name="with_us_since")
	private int withUsSince;
	
	@Column(name="no_of_services_provided")
	private int noOfServicesProvided;
	
	@Column(name="contact_no", unique = true, nullable = false)
	private String contactNo;
	
	@Column(name="image_url")
	private String imageUrl;
	
	@Column(name="group_count")
	private int groupCount;
	
	@Column(name="person_or_group")
	private String personOrGroup;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="date_of_birth")
	private String dateOfBirth;
	
	@JsonIgnore
//	@JsonManagedReference(value="labourer-worker-application")
	@OneToMany(mappedBy="labourer",cascade=CascadeType.ALL)
	Set<WorkerApplication> workerApp = new HashSet<WorkerApplication>();
}
