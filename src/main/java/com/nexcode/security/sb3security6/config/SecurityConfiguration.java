package com.nexcode.security.sb3security6.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.nexcode.security.sb3security6.security.JwtAuthenticationEntryPoint;
import com.nexcode.security.sb3security6.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

	@Autowired
	private JwtAuthenticationFilter jwtAuthFilter;

	@Autowired
	private AuthenticationProvider authenticationProvider;

	@Bean
	public JwtAuthenticationEntryPoint entryPoint() {
		return new JwtAuthenticationEntryPoint();
	}

	@Bean
	@Order(1)
	public SecurityFilterChain securityFilerChain(HttpSecurity http) throws Exception {

		http.cors(cors -> cors.disable()).csrf(csrf -> csrf.disable());
		http.authenticationProvider(authenticationProvider);
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().exceptionHandling()
				.authenticationEntryPoint(entryPoint());
		http.securityMatcher("/api/**")
				.authorizeHttpRequests(authorize -> authorize.requestMatchers("/api/v1/auth/**").permitAll())
				.authorizeHttpRequests().anyRequest().authenticated().and().httpBasic();
		http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	@Bean
	public SecurityFilterChain formFilterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests(auth -> auth.requestMatchers("/signup", "/register-success/**", "/account")
				.permitAll().anyRequest().authenticated())

				.formLogin().loginPage("/login").defaultSuccessUrl("/").failureUrl("/login?error=true").permitAll()
				.loginProcessingUrl("/process_login").and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/").permitAll();
		return http.build();
	}

}
