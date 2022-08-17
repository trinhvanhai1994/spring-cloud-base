package com.ominext.admin;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAdminServer
public class AdminServiceApplication {

	private final String adminContextPath;

	public AdminServiceApplication(AdminServerProperties adminServerProperties) {
		this.adminContextPath = adminServerProperties.getContextPath();
	}

	public static void main(String[] args) {
		new SpringApplicationBuilder(AdminServiceApplication.class)
				.web(WebApplicationType.REACTIVE)
				.run(args);
	}

	@Bean
	public SecurityWebFilterChain securityWebFilterChainSecure(ServerHttpSecurity http) {

		return http.authorizeExchange()
				.pathMatchers(adminContextPath + "/assets/**").permitAll()
				.pathMatchers(adminContextPath + "/login").permitAll()
				.anyExchange().authenticated()
				.and().formLogin().loginPage(adminContextPath + "/login")
				.and().logout().logoutUrl(adminContextPath + "/logout")
				.and().httpBasic()
				.and().csrf().disable().build();

	}
}
