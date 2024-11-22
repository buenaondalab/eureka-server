package com.buenaondalab.garden.eurekaserver;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@SpringBootTest(webEnvironment = DEFINED_PORT, properties = "spring.profiles.active=test")
class EurekaServerApplicationTests {

	@Value(value = "${spring.security.user.name}")
	String username;
	@Value(value = "${spring.security.user.password}")
	String password;


	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	void catalogNoAuth() {
		@SuppressWarnings("rawtypes")
		ResponseEntity<Map> entity = testRestTemplate
			.getForEntity("/eureka/apps", Map.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
	}

	@Test
	void catalogLoads() {
		@SuppressWarnings("rawtypes")
		ResponseEntity<Map> entity = testRestTemplate
			.withBasicAuth(username, password)
			.getForEntity("/eureka/apps", Map.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	void adminLoads() {
		@SuppressWarnings("rawtypes")
		ResponseEntity<Map> entity = testRestTemplate
			.withBasicAuth(username, password)
			.getForEntity("/actuator/env", Map.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}


}
