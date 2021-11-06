package api.restaurant.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
import api.restaurant.entity.enums.Profile;
import api.restaurant.entity.enums.TypeClient;
import api.restaurant.repository.AddressRepository;
import api.restaurant.repository.CategoryRepository;
import api.restaurant.repository.CityRepository;
import api.restaurant.repository.ClientRepository;
import api.restaurant.repository.OrderedItemRepository;
import api.restaurant.repository.PaymentRepository;
import api.restaurant.repository.PedidoRepository;
import api.restaurant.repository.ProductRepository;
import api.restaurant.repository.StateRepository;

@Service
public class DBService {

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
	ClientRepository clienteRepository;
	@Autowired
	AddressRepository enderecoRepository;
	@Autowired
	StateRepository estadoRepository;
	@Autowired
	OrderedItemRepository itemPedidoRepository;
	@Autowired
	 BCryptPasswordEncoder pe;

	public void instantiateTestDatabase() throws ParseException {
		Category cat1 = new Category(null, "Informática");
		Category cat2 = new Category(null, "Escritório");
		Category cat3 = new Category(null, "Cama mesa e banho");
		Category cat4 = new Category(null, "Eletrônicos");
		Category cat5 = new Category(null, "Jardinagem");
		Category cat6 = new Category(null, "Decoração");
		Category cat7 = new Category(null, "Perfumaria");
		
		
		
		Product p1 = new Product(null, "Computador", 2000.00);
		Product p2 = new Product(null, "Impressora", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);
		Product p4 = new Product(null, "Mesa de escritório", 300.00);
		Product p5 = new Product(null, "Toalha", 50.00);
		Product p6 = new Product(null, "Colcha", 200.00);
		Product p7 = new Product(null, "TV true color", 1200.00);
		Product p8 = new Product(null, "Roçadeira", 800.00);
		Product p9 = new Product(null, "Abajour", 100.00);
		Product p10 = new Product(null, "Pendente", 180.00);
		Product p11 = new Product(null, "Shampoo", 90.00);

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
		proRepo.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11));

		State est1 = new State(null, "Minas Gerais");
		State est2 = new State(null, "São Paulo");

		City c1 = new City(null, "Uberlândia", est1);
		City c2 = new City(null, "São Paulo", est2);
		City c3 = new City(null, "Campinas", est2);

		est1.getCities().addAll(Arrays.asList(c1));
		est2.getCities().addAll(Arrays.asList(c2, c3));
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		ctRepo.saveAll(Arrays.asList(c1, c2, c3));

		Client cli1 = new Client(null, "Maria Silva", "moreiradaavi@gmail.com", "36378912377", TypeClient.PESSOA_FISICA,pe.encode("123"));
		cli1.getTelephones().addAll(Arrays.asList("27363323", "93838393"));
		
		Client cli2 = new Client(null, "Jayane Feitosa", "jayanefeitosa39@gmail.com", "69831519094", TypeClient.PESSOA_FISICA,pe.encode("123"));
		cli2.getTelephones().addAll(Arrays.asList("22463323", "45638393"));
		cli2.addPerfil(Profile.ADMIN);
		
		Address e1 = new Address(null, "Rua das Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Address e2 = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		Address e3 = new Address(null, "Manoel Soares", "107", null, "Centro", "63077012", cli2, c2);

		cli1.getAdresses().addAll(Arrays.asList(e1, e2));
		cli2.getAdresses().addAll(Arrays.asList(e3));

		clienteRepository.saveAll(Arrays.asList(cli1,cli2));

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
		OrderedItem ip3 = new OrderedItem(ped2, p2, 800.00, 1, 100.00);

		ped1.getItens().addAll(Arrays.asList(ip1));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}
}
