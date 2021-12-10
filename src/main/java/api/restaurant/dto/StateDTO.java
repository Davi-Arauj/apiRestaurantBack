package api.restaurant.dto;

import java.io.Serializable;

import api.restaurant.entity.State;
import lombok.Data;

@Data
public class StateDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	
	public StateDTO(State obj) {
		this.id = obj.getId();
		this.name = obj.getName();
	}
}
