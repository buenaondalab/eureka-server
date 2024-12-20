package com.buenaondalab.garden.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApplication.class, args);
	}

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {	
		http.authorizeHttpRequests(authz -> authz
			.anyRequest().authenticated())
			.httpBasic(withDefaults());
        http.csrf(csrf -> csrf.ignoringRequestMatchers("/eureka/**"));
		return http.build();
	}

}
