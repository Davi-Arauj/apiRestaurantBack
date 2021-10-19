package api.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.restaurant.entity.Pedido;
import api.restaurant.repository.PedidoRepository;
import api.restaurant.service.exception.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository requestRepo;

	public Pedido buscar(Long id) {
		 return requestRepo.findById(id)
	                .orElseThrow(() -> new ObjectNotFoundException("Object not found"));
	}
	

}
