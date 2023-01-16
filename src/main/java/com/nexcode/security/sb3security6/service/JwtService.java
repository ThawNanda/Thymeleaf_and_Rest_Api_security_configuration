package com.nexcode.security.sb3security6.service;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;

public interface JwtService {

	String extractUsername(String jwt);

	boolean isTokenValid(String jwt, UserDetails userDetails);

	String generateToken(UserDetails userDetails);

	Claims getClaims(String jwt);

}
