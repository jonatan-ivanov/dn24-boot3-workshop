package com.example.dogservice.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest
public class DogServiceIntegrationTests {

	@Container
	@ServiceConnection
	static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:14.1-alpine");

	@Autowired
	OwnerService ownerService;

	@Test
	void getDogsByOwner() {
		List<String> dogNames = this.ownerService.getOwnedDogNames("Scott");
		assertThat(dogNames).hasSize(2);
		assertThat(dogNames).containsExactlyInAnyOrder("Snoopy", "Goofy");
		dogNames = this.ownerService.getOwnedDogNames("Jonatan");
		assertThat(dogNames).hasSize(1);
		assertThat(dogNames).containsExactlyInAnyOrder("Clifford");
	}

}

