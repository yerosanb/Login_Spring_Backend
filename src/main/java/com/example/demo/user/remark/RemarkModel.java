package com.example.demo.user.remark;

public class RemarkModel {
	
	private Long id;
 	private String title;
 	private String description;
 	private String created_date;
 	private String status;
	private String availability;
	private String firstname;
	private String middlename;
	private String lastname;
	private long userId;
	private long remarkId;
	private String email;
	public RemarkModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RemarkModel(Long id, String title, String description, String created_date, String status,
			String availability, String firstname, String middlename, String lastname, long userId, long remarkId,
			String email) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.created_date = created_date;
		this.status = status;
		this.availability = availability;
		this.firstname = firstname;
		this.middlename = middlename;
		this.lastname = lastname;
		this.userId = userId;
		this.remarkId = remarkId;
		this.email = email;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCreated_date() {
		return created_date;
	}
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
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
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getRemarkId() {
		return remarkId;
	}
	public void setRemarkId(long remarkId) {
		this.remarkId = remarkId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "remarkModel [id=" + id + ", title=" + title + ", description=" + description + ", created_date="
				+ created_date + ", status=" + status + ", availability=" + availability + ", firstname=" + firstname
				+ ", middlename=" + middlename + ", lastname=" + lastname + ", userId=" + userId + ", remarkId="
				+ remarkId + ", email=" + email + "]";
	}

}
