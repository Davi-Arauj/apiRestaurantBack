package api.restaurant.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import api.restaurant.entity.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{

}
