package api.restaurant.resource;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.restaurant.entity.Pedido;
import api.restaurant.service.PedidoService;

@RestController
@RequestMapping(value="/api/v1/pedidos")
public class RequestResource {
	 
	@Autowired
	private PedidoService service;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Pedido> find(@PathVariable Long id) {
		Pedido obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
	}
}
