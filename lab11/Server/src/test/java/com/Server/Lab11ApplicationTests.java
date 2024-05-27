package com.Server;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Lab11ApplicationTests {

	@Test
	void contextLoads() throws Exception {
		new HelloControllerTest().getHello();
	}

}
