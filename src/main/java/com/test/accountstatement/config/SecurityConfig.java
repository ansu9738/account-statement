package com.test.accountstatement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		log.info("SecurityConfig.. configure() .. for authentication method started");
		auth.inMemoryAuthentication().withUser("admin").password("admin").roles("admin").and().withUser("user")
				.password("user").roles("user");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		log.info("SecurityConfig.. configure() .. for authorization method started");
        http.cors().disable()   
             .authorizeRequests()
            .antMatchers("/statements/**").hasRole("admin")
            .antMatchers("/statement/threemonths").hasAnyRole("admin","user")
            .antMatchers("/").permitAll()
            .and().formLogin();

	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
