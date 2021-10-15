package api.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.restaurant.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
