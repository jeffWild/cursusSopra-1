package com.courtalon.fistSecurityForm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// @Configuration: cette annotation indique a spring que cette classe sert a configurer
// notre application
@Configuration
@EnableWebSecurity //activer la securité dédiee aux app webs
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UtilisateurDetailsService utilisateurDetailsService;
	public UtilisateurDetailsService getUtilisateurDetailsService() {return utilisateurDetailsService;}
	public void setUtilisateurDetailsService(UtilisateurDetailsService utilisateurDetailsService) {this.utilisateurDetailsService = utilisateurDetailsService;}

	// cette méthode configure l'identification des utilisateurs
	// en général, cela passe par une table en base de donnée
	// et un formulaire de login
	// ce n'est pas ici que l'on configure les urls/form/etc
	// c'est ici qu'oon indique a spring ou/comment trouver les
	// comptes utilisateurs et ce a quoi ils on droit "authorities"
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*auth.inMemoryAuthentication()
			.withUser("admin").password("admin").roles("USER", "ADMIN").and()
			.withUser("vincent").password("P@ssw0rd").roles("USER");*/
		/*auth.jdbcAuthentication().dataSource(null)
			.usersByUsernameQuery("select username, password, enabled form user where username=?")
			.authoritiesByUsernameQuery("...");
		*/
		auth.userDetailsService(utilisateurDetailsService)
			.passwordEncoder(new BCryptPasswordEncoder());
			//.passwordEncoder(new PlaintextPasswordEncoder()); //ATTENTION, NE PAS UTILISER CELUI-CI
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
								.permitAll().and()
			.logout().logoutUrl("/logout")
					.logoutSuccessUrl("/public/")
					.and() // j_spring_security_logout
			.formLogin().and() // spring proposera le login via un formulaire web
			.httpBasic(); // pas de cryptage des connexions (pas de https)
 

		
//		.authenticated() // quelqu'un qui est authentifié
//		.antMatchers("/public/**")
//		.anonymous().and()// pour /public, tous le monde y a acces

	}
	
	
	
}
