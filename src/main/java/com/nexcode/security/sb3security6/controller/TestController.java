package com.nexcode.security.sb3security6.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

	@PostMapping("/test")
	@PreAuthorize("hasRole('USER')")
	public String testing(@AuthenticationPrincipal UserDetails userDetails) {

		System.out.println(userDetails.getUsername());

		return "Thist is Testing";
	}
}
