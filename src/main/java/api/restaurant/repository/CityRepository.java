package api.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.restaurant.entity.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long>{

}
