package sh.tech.tourmanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TourManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(TourManagementSystemApplication.class, args);
	}

}
