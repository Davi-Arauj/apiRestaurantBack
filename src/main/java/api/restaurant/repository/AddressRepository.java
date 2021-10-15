package api.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.restaurant.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{

}
