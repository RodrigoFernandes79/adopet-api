package com.adopet.apiadopet.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.adopet.apiadopet.configurations.SecurityConfigurations;

@Service
public class AutenticacaoService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		UserDetails usuario = usuarioRepository.findByEmail(email);
		if (usuario == null) {
			throw new UsernameNotFoundException(email);
		}

		return usuario;
	}

	public void configure(SecurityConfigurations securityConfigurations, AuthenticationManagerBuilder auth)
			throws Exception {
		// configurando a autenticação em memória:
		auth
				.userDetailsService(this)
				.passwordEncoder(securityConfigurations.passwordEncoder());

	}

}
