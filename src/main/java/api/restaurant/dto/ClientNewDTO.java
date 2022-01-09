package api.restaurant.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import api.restaurant.service.validation.ClientInsert;
import lombok.Data;

@Data
@ClientInsert
public class ClientNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 3, max = 120, message = "O tamanho deve ser entre 3 e 120 caracteres")
	private String name;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message = "Email inválido")
	private String email;

	@NotEmpty(message = "O preenchimento é obrigatório!")
	private String cpfOuCnpj;

	private Integer typeClient;

	@NotEmpty(message = "O preenchimento é obrigatório!")
	private String password;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String publicPlace;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String number;

	private String complement;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String district;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String cep;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String telephone1;
	private String telephone2;
	private String telephone3;

	private Long cityId;

	public ClientNewDTO() {
	}
}
