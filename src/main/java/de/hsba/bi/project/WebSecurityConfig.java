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
				.antMatchers("/").permitAll()
//					Wenn etwas unter /admin liegt, kann nur jemand mit der Rolle ADMIN darauf zugreifen:
				.antMatchers("/createEvent").hasRole("ADMIN")
//				.anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
				.logout()
				.permitAll();

	}

	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/h2-console/**");
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