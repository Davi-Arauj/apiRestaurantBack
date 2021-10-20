package api.restaurant.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.restaurant.entity.OrderedItem;
import api.restaurant.entity.PaymentWithBoleto;
import api.restaurant.entity.Pedido;
import api.restaurant.entity.enums.Paymentstate;
import api.restaurant.repository.OrderedItemRepository;
import api.restaurant.repository.PaymentRepository;
import api.restaurant.repository.PedidoRepository;
import api.restaurant.service.exception.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository requestRepo;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PaymentRepository pagamentoRepository;
	
	@Autowired
	private OrderedItemRepository itemPedidoRepository;
	
	@Autowired
	private ProductService produtoService;
	
	@Autowired
	private ClientService clienteService;

	public Pedido buscar(Long id) {
		 return requestRepo.findById(id)
	                .orElseThrow(() -> new ObjectNotFoundException("Object not found"));
	}
	
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstant(new Date());
		obj.setClient(clienteService.findById(obj.getClient().getId()));
		obj.getPayment().setState(Paymentstate.PENDENTE.getCod());
		obj.getPayment().setRequest(obj);
		if (obj.getPayment() instanceof PaymentWithBoleto) {
			PaymentWithBoleto pagto = (PaymentWithBoleto) obj.getPayment();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstant());
		}
		obj = requestRepo.save(obj);
		pagamentoRepository.save(obj.getPayment());
		for (OrderedItem ip : obj.getItens()) {
			ip.setDiscount(0.0);
			ip.setProduct(produtoService.findById(ip.getProduct().getId()));
			ip.setPrice(ip.getProduct().getPrice());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
	//	emailService.sendOrderConfirmationEmail(obj);
		return obj;
	}

}
