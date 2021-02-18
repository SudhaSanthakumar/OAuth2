package com.auth.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class UserResourceServerConfig extends ResourceServerConfigurerAdapter {
	

private static final String RESOURCE_ID = "user-resource";
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		
		resources.resourceId(RESOURCE_ID);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		

		http
		.anonymous().disable()
		.authorizeRequests().antMatchers("/users**").authenticated()
		.and()
		.exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
		
	
		
	}
}
