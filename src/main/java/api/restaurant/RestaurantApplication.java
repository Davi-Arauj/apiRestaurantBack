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

	@Autowired
	CityRepository ctRepo;
	@Autowired
	CategoryRepository catRepo;
	@Autowired
	ProductRepository proRepo;
	@Autowired
	PedidoRepository pedidoRepository;
	@Autowired
	PaymentRepository pagamentoRepository;
	@Autowired
	ClientRepository  clienteRepository;
	@Autowired
	AddressRepository enderecoRepository;
	@Autowired
	StateRepository estadoRepository;
	@Autowired
	OrderedItemRepository  itemPedidoRepository;
	@Autowired
	ProductMaping productMapper;


	@Override
	public void run(String... args) throws Exception {

		Category cat1 = new Category(null, "Informática");
		Category cat2 = new Category(null, "Escritório");
		Category cat3 = new Category(null, "Cama mesa e banho");
		Category cat4 = new Category(null, "Eletrônicos");
		Category cat5 = new Category(null, "Jardinagem");
		Category cat6 = new Category(null, "Decoração");
		Category cat7 = new Category(null, "Perfumaria");
		
		
		ProductDTO p1 = new ProductDTO(null, "Computador", 2000.00);
		ProductDTO p2 = new ProductDTO(null, "Impressora", 800.00);
		ProductDTO p3 = new ProductDTO(null, "Mouse", 80.00);
		ProductDTO p4 = new ProductDTO(null, "Mesa de escritório", 300.00);
		ProductDTO p5 = new ProductDTO(null, "Toalha", 50.00);
		ProductDTO p6 = new ProductDTO(null, "Colcha", 200.00);
		ProductDTO p7 = new ProductDTO(null, "TV true color", 1200.00);
		ProductDTO p8 = new ProductDTO(null, "Roçadeira", 800.00);
		ProductDTO p9 = new ProductDTO(null, "Abajour", 100.00);
		ProductDTO p10 = new ProductDTO(null, "Pendente", 180.00);
		ProductDTO p11 = new ProductDTO(null,"Shampoo",90.00);

		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2));

		cat2.getProducts().addAll(Arrays.asList(p2, p4));
		cat3.getProducts().addAll(Arrays.asList(p5, p6));
		cat4.getProducts().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProducts().addAll(Arrays.asList(p8));
		cat6.getProducts().addAll(Arrays.asList(p9, p10));
		cat7.getProducts().addAll(Arrays.asList(p11));

		p1.getCategories().addAll(Arrays.asList(cat1, cat4));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategories().addAll(Arrays.asList(cat1, cat4));
		p4.getCategories().addAll(Arrays.asList(cat2));
		p5.getCategories().addAll(Arrays.asList(cat3));
		p6.getCategories().addAll(Arrays.asList(cat3));
		p7.getCategories().addAll(Arrays.asList(cat4));
		p8.getCategories().addAll(Arrays.asList(cat5));
		p9.getCategories().addAll(Arrays.asList(cat6));
		p10.getCategories().addAll(Arrays.asList(cat6));
		p11.getCategories().addAll(Arrays.asList(cat7));		

		catRepo.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		proRepo.saveAll(Arrays.asList(productMapper.toModel(p1), 
				productMapper.toModel(p2), 
				productMapper.toModel(p3),
				productMapper.toModel(p4),
				productMapper.toModel(p5),
				productMapper.toModel(p6),
				productMapper.toModel(p7),
				productMapper.toModel(p8),
				productMapper.toModel(p9),
				productMapper.toModel(p10),
				productMapper.toModel(p11)));

		State est1 = new State(null, "Minas Gerais");
		State est2 = new State(null, "São Paulo");
		
		City c1 = new City(null, "Uberlândia", est1);
		City c2 = new City(null, "São Paulo", est2);
		City c3 = new City(null, "Campinas", est2);

		est1.getCities().addAll(Arrays.asList(c1));
		est2.getCities().addAll(Arrays.asList(c2, c3));
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		ctRepo.saveAll(Arrays.asList(c1, c2, c3));
		
		Client cli1 = new Client(null, "Maria Silva", "maria@gmail.com", "36378912377", TypeClient.PESSOA_FISICA);
		
		cli1.getTelephones().addAll(Arrays.asList("27363323", "93838393"));
		
		Address e1 = new Address(null, "Rua das Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Address e2 = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
	

		cli1.getAdresses().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
										
		Payment pagto1 = new PaymentWithCard(null, Paymentstate.QUITADO, ped1, 6);
		ped1.setPayment(pagto1);
											 
		Payment pagto2 = new PaymentWithBoleto(null, Paymentstate.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPayment(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		OrderedItem ip1 = new OrderedItem(ped1, p1, 2000.00, 1, 00.00);
		OrderedItem ip2 = new OrderedItem(ped1, p3, 80.00, 1, 00.00);
		OrderedItem ip3 = new OrderedItem(ped2, p2, 800.00,1,100.00);
		

		ped1.getItens().addAll(Arrays.asList(ip1));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2,ip3));
		
	}

}
