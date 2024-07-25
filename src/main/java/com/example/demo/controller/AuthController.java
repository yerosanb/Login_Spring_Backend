package com.example.demo.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginRequest;
import com.example.demo.reponse.ApiResponse;
import com.example.demo.services.AuthService;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@RequestMapping(value = "get_login_trial/{email}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> get_login_trial(HttpServletResponse httpServletResponse, @PathVariable("email") String email) {
		return ApiResponse.response(authService.get_login_trial(email));
	}

	@RequestMapping(value = "login_other_device_browser/{email}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Boolean> login_other_device_browser( @PathVariable("email") String email) {
		Boolean result = authService.login_other_device_browser(email);
		System.out.println("result=" + result);
		if (result != null)
			if (result == true)
				return ApiResponse.response(true);
		return ApiResponse.response(false);
		
	}

	@RequestMapping(value = "login", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> login(HttpServletResponse httpServletResponse, HttpServletRequest request,
			@Validated @RequestBody LoginRequest loginRequest) {
		return ApiResponse.response(authService.login(loginRequest, httpServletResponse, request));
	}
	
	
	@RequestMapping(value = "login_", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> login_(HttpServletResponse httpServletResponse, HttpServletRequest request,
			@Validated @RequestBody LoginRequest loginRequest) {
		return ApiResponse.response(authService.login_(loginRequest, httpServletResponse, request));
	}
	
	@RequestMapping(value = "forgot_password/{email}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Boolean> forgot_password(HttpServletRequest request,
			@PathVariable("email") String email) {
		return ApiResponse.response(authService.forgot_password(email, request));
	}
	@RequestMapping(value = "clear-cookies", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> clearCookies(HttpServletResponse response, HttpServletRequest request) {
		return ApiResponse.response(authService.clearCookies(response, request));
	}
	
	@RequestMapping(value = "refresh-token", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("/refresh-token");
		return ApiResponse.response(authService.refreshToken(request, response));
	}
}
