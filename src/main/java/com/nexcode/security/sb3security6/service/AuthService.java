package com.nexcode.security.sb3security6.service;

import com.nexcode.security.sb3security6.model.request.AuthenticationRequest;
import com.nexcode.security.sb3security6.model.request.RegisterRequest;
import com.nexcode.security.sb3security6.model.response.AuthenticationResponse;

public interface AuthService {

	AuthenticationResponse register(RegisterRequest request);

	AuthenticationResponse authenticate(AuthenticationRequest request);

}
