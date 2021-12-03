package api.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import api.restaurant.service.S3Service;

@SpringBootApplication
public class RestaurantApplication implements CommandLineRunner {
	
	
	@Autowired
	private S3Service s3Service;


	public static void main(String[] args) {
		SpringApplication.run(RestaurantApplication.class, args);

	} 
	
	@Override
	public void run(String... args) throws Exception {	
		s3Service.uploadFile("/home/d/Documents/workspace-spring-tool-suite-4-4.11.0.RELEASE/restaurant/ft-jayane-rosto.jpeg");
	}	

}
