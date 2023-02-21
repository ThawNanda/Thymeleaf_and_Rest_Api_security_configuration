package com.nexcode.security.sb3security6.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import com.nexcode.security.sb3security6.model.entity.User;

import io.jsonwebtoken.Claims;

public interface JwtService {

	String extractUsername(String jwt);

	boolean isTokenValid(String jwt, UserDetails userDetails);

	String generateToken(UserDetails userDetails);

	Claims getClaims(String jwt);

	String generateToken(Authentication authentication);

	String generateToken(User user);

}
