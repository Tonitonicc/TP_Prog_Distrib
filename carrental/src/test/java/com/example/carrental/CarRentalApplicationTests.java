package com.example.carrental;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CarRentalApplicationTests {

	@Autowired
	private CarController carController;

	@Test
	void contextLoads() {
		assertThat(carController).isNotNull();
	}
}