package com.example.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="login_details")
public class LoginDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_id")
	private int userId;
	
	@Column(name="user_name", unique = true)
	private String userName;
		
	@Column(name="password")
	private String password;
	
	@Column(name="type")
	private String type;

	@JsonIgnore
//	@JsonManagedReference(value="labourer-loginDetails")
	@OneToOne(mappedBy="loginDetails",cascade=CascadeType.ALL)
	private Labourer labourer;
	
	@JsonIgnore
//	@JsonManagedReference(value="contractor-loginDetails")
	@OneToOne(mappedBy="loginDetails",cascade=CascadeType.ALL)
	private Contractor contractor;
}
