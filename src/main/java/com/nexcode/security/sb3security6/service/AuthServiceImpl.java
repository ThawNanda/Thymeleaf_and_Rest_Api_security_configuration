package com.nexcode.security.sb3security6.service;

import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nexcode.security.sb3security6.model.entity.Role;
import com.nexcode.security.sb3security6.model.entity.RoleName;
import com.nexcode.security.sb3security6.model.entity.User;
import com.nexcode.security.sb3security6.model.exception.BadRequestException;
import com.nexcode.security.sb3security6.model.request.AuthenticationRequest;
import com.nexcode.security.sb3security6.model.request.RegisterRequest;
import com.nexcode.security.sb3security6.model.response.AuthenticationResponse;
import com.nexcode.security.sb3security6.repository.RoleRepository;
import com.nexcode.security.sb3security6.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final UserRepository userRepository;

	private final PasswordEncoder encoder;

	private final JwtService jwtService;

	private final AuthenticationManager authenticationManager;

	private final RoleRepository roleRepository;

	@Override
	public AuthenticationResponse register(RegisterRequest request) {

		Role role = roleRepository.findByName(RoleName.ROLE_USER)
				.orElseThrow(() -> new BadRequestException("Role not exist"));

		User user = User.builder().username(request.getUsername()).password(encoder.encode(request.getPassword()))
				.roles(Collections.singleton(role)).build();
		userRepository.save(user);

		// String jwt = jwtService.generateToken(user);

		// return AuthenticationResponse.builder().token(jwt).build();

		return null;
	}

	@Override
	public AuthenticationResponse authenticate(AuthenticationRequest request) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		String role = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));

		System.out.println(role);

		if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
			String jwt = jwtService.generateToken(authentication);
			return AuthenticationResponse.builder().token(jwt).build();
		} else {
			System.out.println("haha");
		}
		return null;
	}

}
