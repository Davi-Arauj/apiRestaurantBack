package api.restaurant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.restaurant.entity.City;
import api.restaurant.repository.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;
	
	public List<City> findByState(Integer stateId){
		return cityRepository.findCity(stateId);
		
	}
}
