package com.adopet.apiadopet.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.adopet.apiadopet.security.JwtAuthFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

	@Autowired
	private JwtAuthFilter jwtAuthFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		return http.csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().authorizeRequests()
		.requestMatchers(HttpMethod.POST,"/login/**").permitAll()
		.requestMatchers(HttpMethod.GET,"/tutores/**")
		.hasAnyRole("ABRIGO","TUTOR")
		.requestMatchers(HttpMethod.POST,"/tutores/**").permitAll()
		.requestMatchers("/tutores/**")
		.hasRole("TUTOR")
		.requestMatchers(HttpMethod.GET,"/pets/**")
		.hasAnyRole("ABRIGO","TUTOR")
		.requestMatchers("/pets/**")
		.hasRole("ABRIGO")
		.requestMatchers(HttpMethod.GET,"/abrigos/**")
		.hasAnyRole("ABRIGO","TUTOR")
		.requestMatchers(HttpMethod.POST,"/abrigos/**").permitAll()
		.requestMatchers("/abrigos/**")
		.hasRole("ABRIGO")
		.requestMatchers(HttpMethod.POST,"/adocao/**")
		.hasRole("TUTOR")
		.requestMatchers(HttpMethod.DELETE,"/adocao/**")
		.hasRole("ABRIGO")
		.requestMatchers("/v3/api-docs/**","/swagger-ui/**").permitAll()
		.anyRequest().authenticated()
		.and().addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
		.build();
	}

	@Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }

    @Bean  //método para encriptografar senhas
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
