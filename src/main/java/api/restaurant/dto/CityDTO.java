package api.restaurant.dto;

import java.io.Serializable;

import api.restaurant.entity.City;
import lombok.Data;

@Data
public class CityDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	
	public CityDTO(City obj) {
		this.id = obj.getId();
		this.name = obj.getName();
	}
}
