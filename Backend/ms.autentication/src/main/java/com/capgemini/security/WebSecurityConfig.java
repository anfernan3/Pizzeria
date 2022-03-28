package com.capgemini.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
//		config.setAllowCredentials(true);
//		config.addAllowedOrigin("http://localhost:4200");
		config.setAllowCredentials(false);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}

	@Value("${jwt.secret}")
	private String SECRET;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and()
			.csrf().disable()			
			.addFilterAfter(new JWTAuthorizationFilter(SECRET), UsernamePasswordAuthenticationFilter.class)
			.authorizeRequests()
//			.antMatchers("/actuator/**").permitAll()
//			.antMatchers("/login").permitAll()
//			.antMatchers(HttpMethod.POST, "/login").permitAll()
//			.antMatchers(HttpMethod.GET, "/login").permitAll()
//			.antMatchers(HttpMethod.GET, "/admin").hasRole("ADMIN")
			.antMatchers(
					"/login/**",
					"/api/v3/users/files/*",
					"/api/v3/token/*",
					"/v3/api-docs",
					"/swagger-resources/**",
					"/swagger-ui/**",
					"/webjars/**",
					"/api/v1/",
					"/api/v1/uploads/**",
					"/api/v1/favicon.ico"
					).permitAll()
			.anyRequest().authenticated();
	}
}
