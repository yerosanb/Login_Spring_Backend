package com.example.demo.reponse;

import org.springframework.http.ResponseEntity;
import static org.springframework.http.HttpStatus.OK;

public class ApiResponse {
	
	
	//return ResponseEntity OF Object and status by accepting only the Object
	public static ResponseEntity<Object> response(Object value) {
		return new ResponseEntity<>(value, OK);
	}
	
	public static ResponseEntity<Boolean> response(Boolean value) {
		return new ResponseEntity<>(value, OK);
	}

}
