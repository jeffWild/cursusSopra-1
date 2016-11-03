package com.courtalon.fistSecurityForm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// @Configuration: cette annotation indique a spring que cette classe sert a configurer
// notre application
@Configuration
@EnableWebSecurity //activer la securité dédiee aux app webs
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// cette méthode configure l'identification des utilisateurs
	// en général, cela passe par une table en base de donnée
	// et un formulaire de login
	// ce n'est pas ici que l'on configure les urls/form/etc
	// c'est ici qu'oon indique a spring ou/comment trouver les
	// comptes utilisateurs et ce a quoi ils on droit "authorities"
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("admin").password("admin").roles("USER", "ADMIN").and()
			.withUser("vincent").password("P@ssw0rd").roles("USER");
	}

	// on configure quel role a acces a quel partie du site
	// ainsi que divers mecanisme lie au requette http et a la securité
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/admin/**") // filtrage des requettes pour /admin
								.hasRole("ADMIN")  // uniquement ceux qui ont le role "ADMIN"
								.antMatchers("/user/**")
								.hasRole("USER")
								.antMatchers("/public/**")
								.anonymous().and()	// pour /public, tous le monde y a acces
			.httpBasic().and() // pas de cryptage des connexions (pas de https)
			.formLogin(); // spring proposera le login via un formulaire web
								
	}
	
	
	
}
