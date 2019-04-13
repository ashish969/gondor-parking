package org.target.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAdminServer
@SpringBootApplication
public class ParkingAdminApplication {
	public static void main(String[] args) {
		SpringApplication.run(ParkingAdminApplication.class, args);
	}

}

