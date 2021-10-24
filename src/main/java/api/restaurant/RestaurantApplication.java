package api.restaurant;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import api.restaurant.dto.ProductDTO;
import api.restaurant.entity.Address;
import api.restaurant.entity.Category;
import api.restaurant.entity.City;
import api.restaurant.entity.Client;
import api.restaurant.entity.OrderedItem;
import api.restaurant.entity.Payment;
import api.restaurant.entity.PaymentWithBoleto;
import api.restaurant.entity.PaymentWithCard;
import api.restaurant.entity.Pedido;
import api.restaurant.entity.Product;
import api.restaurant.entity.State;
import api.restaurant.entity.enums.Paymentstate;
import api.restaurant.entity.enums.TypeClient;
import api.restaurant.mapper.ProductMaping;
import api.restaurant.repository.AddressRepository;
import api.restaurant.repository.CategoryRepository;
import api.restaurant.repository.CityRepository;
import api.restaurant.repository.ClientRepository;
import api.restaurant.repository.OrderedItemRepository;
import api.restaurant.repository.PaymentRepository;
import api.restaurant.repository.PedidoRepository;
import api.restaurant.repository.ProductRepository;
import api.restaurant.repository.StateRepository;

@SpringBootApplication
public class RestaurantApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantApplication.class, args);

	}
	
	
	

	@Override
	public void run(String... args) throws Exception {
		
	}

}
