package com.nexcode.security.sb3security6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

	@GetMapping
	public String showLogin(@RequestParam(value = "error", required = false) boolean error, Model model) {
		if (error) {
			String errorMessage = "Invalid username or password!";
			model.addAttribute("errorMessage", errorMessage);
		}
		return "add-css-js";
	}
}
