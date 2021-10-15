package api.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.restaurant.entity.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long>{

}
