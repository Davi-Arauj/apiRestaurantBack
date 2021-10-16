package api.restaurant.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import api.restaurant.entity.Client;
import lombok.Data;

@Data
public class ClientDTO {

	private Long id;
	@NotEmpty(message = "O preenchimento é obrigatório!")
	@Length(min = 3, max = 120, message = "O tamanho deve ser entre 3 e 120 caracteres")
	private String name;

	@NotEmpty(message = "O preenchimento é obrigatório!")
	@Email(message = "O email não é valido")
	private String email;
	
	
	
	
	public ClientDTO() {
	}

	public ClientDTO(Client obj) {
		id = obj.getId();
		name = obj.getName();
		email = obj.getEmail();
	}
}
