package com.nexcode.security.sb3security6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

	@GetMapping
	public String getIndex() {
		return "index";
	}

	@GetMapping("/style")
	public String getStyle() {
		return "add-css-js";
	}
}
