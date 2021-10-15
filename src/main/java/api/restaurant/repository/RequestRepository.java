package api.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.restaurant.entity.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long>{

}
