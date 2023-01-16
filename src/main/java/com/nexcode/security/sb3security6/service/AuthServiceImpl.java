package com.nexcode.security.sb3security6.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nexcode.security.sb3security6.model.entity.RoleName;
import com.nexcode.security.sb3security6.model.entity.User;
import com.nexcode.security.sb3security6.model.request.AuthenticationRequest;
import com.nexcode.security.sb3security6.model.request.RegisterRequest;
import com.nexcode.security.sb3security6.model.response.AuthenticationResponse;
import com.nexcode.security.sb3security6.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.var;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final UserRepository userRepository;

	private final PasswordEncoder encoder;

	private final JwtService jwtService;

	private final AuthenticationManager authenticationManager;

	@Override
	public AuthenticationResponse register(RegisterRequest request) {
		var user = User.builder().username(request.getUsername()).password(encoder.encode(request.getPassword()))
				.role(RoleName.ROLE_USER).build();
		userRepository.save(user);
		var jwt = jwtService.generateToken(user);
		return AuthenticationResponse.builder().token(jwt).build();
	}

	@Override
	public AuthenticationResponse authenticate(AuthenticationRequest request) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
			var user = userRepository.findByUsername(request.getUsername()).orElseThrow();
			var jwt = jwtService.generateToken(user);

			return AuthenticationResponse.builder().token(jwt).build();
		}
		return null;
	}

}
