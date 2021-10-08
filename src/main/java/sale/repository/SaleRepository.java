package sale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sale.entity.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long>{

}
