package com.nexcode.security.sb3security6.controller.rest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

	@GetMapping("/testing")
	@PreAuthorize("hasRole('USER')")
	public void testing(@AuthenticationPrincipal UserDetails userDetails) {

		System.out.println(userDetails.getUsername());

	}
}
