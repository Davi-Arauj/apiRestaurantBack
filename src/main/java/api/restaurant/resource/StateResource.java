package api.restaurant.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.restaurant.dto.CityDTO;
import api.restaurant.dto.StateDTO;
import api.restaurant.entity.City;
import api.restaurant.entity.State;
import api.restaurant.service.CityService;
import api.restaurant.service.StateService;

@RestController
@RequestMapping(value="/api/v1/state")
public class StateResource {

	@Autowired
	private StateService stateService;
	@Autowired
	private CityService cityService;
	
	
	@GetMapping
	public ResponseEntity<List<StateDTO>> findAllState(){
		List<State> list = stateService.findAll();
		List<StateDTO> listDTO = list.stream().map(obj -> new StateDTO(obj)).collect(Collectors.toList()); 
		return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping(value="/{stateId}/city")
	public ResponseEntity<List<CityDTO>> findAllCity(@PathVariable Integer stateId){
		List<City> list = cityService.findByState(stateId);
		List<CityDTO> listDTO = list.stream().map(obj -> new CityDTO(obj)).collect(Collectors.toList()); 
		return ResponseEntity.ok().body(listDTO);
	}
}
