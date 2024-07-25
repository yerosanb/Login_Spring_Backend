package com.example.demo.model;

import java.util.List;

import com.example.demo.user.remark.RemarkModel;

//import com.lowagie.text.List;

public class User {
	
	private Long id;
	private String firstname;
	private String middlename;
	private String lastname;
	private String password;
	private String gender;
	private String email;
	private String phonenumber;
	private String role;
	private String reg_date;
	private String status;
	private String availability;
 	private RemarkModel remarkM;
 	
 	private String firsttime;
	
 	private List<Roles> roles;
    
	public User() {
	}
    
	public User(Long id, String firstname, String middlename, String lastname, String password, String gender,
			String email, String phonenumber, String role, String reg_date, String status, String availability,
			String firsttime, List<Roles> roles) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.middlename = middlename;
		this.lastname = lastname;
		this.password = password;
		this.gender = gender;
		this.email = email;
		this.phonenumber = phonenumber;
		this.role = role;
		this.reg_date = reg_date;
		this.status = status;
		this.availability = availability;
		this.firsttime = firsttime;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public String getFirsttime() {
		return firsttime;
	}

	public void setFirsttime(String firsttime) {
		this.firsttime = firsttime;
	}

	public List<Roles> getRoles() {
		return roles;
	}

	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}

}
