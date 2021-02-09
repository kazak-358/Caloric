package net.caloric.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ControllerConstants {

	public static final ResponseEntity<?> CREATED_RESPONSE = new ResponseEntity<>(HttpStatus.CREATED);
	public static final ResponseEntity<?> OK_RESPONSE = new ResponseEntity<>(HttpStatus.OK);
	public static final ResponseEntity<?> NOT_MODIFIED_RESPONSE = new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
}
