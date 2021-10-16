package api.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import api.restaurant.entity.City;
import api.restaurant.repository.CityRepository;

@SpringBootApplication
public class RestaurantApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(RestaurantApplication.class, args);
		
		
	}
	
	@Autowired
	CityRepository ctRepo;

	@Override
	public void run(String... args) throws Exception {

		
		City city = new City(1,null,null);
		ctRepo.save(city);
	}

}
