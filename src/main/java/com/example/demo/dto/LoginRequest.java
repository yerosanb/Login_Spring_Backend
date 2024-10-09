package com.example.demo.dto;

import lombok.Data;

@Data
public class LoginRequest {
	
	private String email;
	private String password;
	private Boolean rememberMe;
	private String browser_type;
	private String browser_version;
	private String ip;

	public LoginRequest() {
	}

	public LoginRequest(String email, String password, Boolean rememberMe, String browser_type, String browser_version,
			String ip) {
		super();
		this.email = email;
		this.password = password;
		this.rememberMe = rememberMe;
		this.browser_type = browser_type;
		this.browser_version = browser_version;
		this.ip = ip;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(Boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	public String getBrowser_type() {
		return browser_type;
	}

	public void setBrowser_type(String browser_type) {
		this.browser_type = browser_type;
	}

	public String getBrowser_version() {
		return browser_version;
	}

	public void setBrowser_version(String browser_version) {
		this.browser_version = browser_version;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	

}
