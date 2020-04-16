package com.mutts_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 
//	@Autowired
//	DataSource dataSource;
 
//	@Autowired
//	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//		auth.jdbcAuthentication().dataSource(dataSource)
//				.usersByUsernameQuery("select user_name, password, enabled from user where user_name=?")
//				.authoritiesByUsernameQuery("select u.user_name, r.role " +
//						"from role r " +
//						"join user_role ur " +
//						"on r.role_id = ur.role_id " +
//						"join user u " +
//						"on u.user_id = ur.user_id " +
//						"where u.user_name=?");
//	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
				.withUser("joe")
				.password("{noop}123")
				.roles("USER")
				.and()
				.withUser("ryan")
				.password("{noop}123")
				.roles("USER");
	}
 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/", "/home","/users").permitAll()
//				.antMatchers("/users").hasRole("USER")
				.anyRequest().authenticated()
				.and().formLogin().loginPage("/login").permitAll()
				.defaultSuccessUrl("/index")
				.and().logout().permitAll();

		http.exceptionHandling().accessDeniedPage("/403");
	}
}