package com.example.dogservice.domain;

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
public class RepositoryIntegrationTests {

	@Container
	@ServiceConnection
	static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:14.1-alpine");

	@Autowired
	OwnerRepository ownerRepository;

	@Autowired
	DogRepository dogRepository;

	@Test
	void getDogs() {
		Owner owner = this.ownerRepository.findByNameIgnoringCase("Jonatan");
		assertThat(owner.getName()).isEqualTo("Jonatan");
		List<Dog> dogs = this.dogRepository.findByOwner(owner);
		assertThat(dogs).hasSize(1);
		assertThat(dogs).extracting("name").containsExactly("Clifford");
	}

}
