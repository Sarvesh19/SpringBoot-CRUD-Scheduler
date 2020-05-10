package com.sarvesh.springboot.demo.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sarvesh.springboot.demo.model.HotelBean;
import com.sarvesh.springboot.demo.repository.AuthRequest;
import com.sarvesh.springboot.demo.service.JwtUtil;

@RestController
@CrossOrigin(origins = { "http://localhost:4200", "http://antonboot.s3-website.ap-south-1.amazonaws.com" })
public class AuthenticationController {

	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping("/")
	public String welcome() {
		return "Welcome to javatechie !!";
	}

	@PostMapping("/authenticate")
	public @ResponseBody ResponseEntity<Map<String, String>> generateToken(@RequestBody AuthRequest authRequest)
			throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
		} catch (Exception ex) {
			throw new Exception("inavalid username/password");
		}
		Map<String, String> token = new HashMap<>();
		token.put("token", jwtUtil.generateToken(authRequest.getUserName()));
		return new ResponseEntity<Map<String, String>>(token, new HttpHeaders(), HttpStatus.OK);

	}
}
