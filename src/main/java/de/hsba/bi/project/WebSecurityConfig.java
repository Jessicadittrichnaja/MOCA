package de.hsba.bi.project;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
				http.authorizeRequests()
//					Wenn etwas unter /eventPlanner liegt, kann nur jemand mit der Rolle TERMINVERWALTER darauf zugreifen:
				.antMatchers("/eventPlanner/**").hasAnyAuthority("TERMINVERWALTER")
				.antMatchers("/HR/**").hasAnyAuthority("PERSONALABTEILUNG")
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
				.logout()
				.permitAll();
				http.requestCache().disable();
	}

	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/h2-console/**");
		web.ignoring().antMatchers("/header");
		web.ignoring().antMatchers("/footer");
		web.ignoring().antMatchers("/main.css");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
//	@Override
//	public UserDetailsService userDetailsService() {
//		UserDetails user =
//			 User.withDefaultPasswordEncoder()
//				.username("user")
//				.password("password")
//				.roles("USER")
//				.build();
//		UserDetails enrico =
//				User.withDefaultPasswordEncoder()
//						.username("Enrico")
//						.password("password")
//						.roles("ADMIN")
//						.build();
//		UserDetails dustin =
//				User.withDefaultPasswordEncoder()
//						.username("Dustin")
//						.password("password")
//						.roles("HR")
//						.build();
//		return new InMemoryUserDetailsManager(user, enrico,dustin);
//	}

}