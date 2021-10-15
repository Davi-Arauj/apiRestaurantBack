package api.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.restaurant.entity.State;

@Repository
public interface StateRepository extends JpaRepository<State, Long>{

}
