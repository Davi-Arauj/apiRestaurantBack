package api.restaurant.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDTO {

	private Long id;
	@NotEmpty(message = "O preenchimento é obrigatório!")
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
	private String name;

	@NotEmpty(message = "O preenchimento é obrigatório!")
	@Email(message = "O email não é valido")
	private String email;
	
}
