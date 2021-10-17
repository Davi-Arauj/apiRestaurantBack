package api.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import api.restaurant.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

	@Transactional(readOnly=true)
	Client findByEmail(String email);
}
