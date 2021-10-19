package api.restaurant.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import api.restaurant.entity.Category;
import lombok.Data;

@Data
public class CategoryDTO {

	private Long id;
	
	@NotEmpty(message = "O preenchimento é obrigatório!")
	@Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres")
	private String description;
	
	public CategoryDTO() {}
	

	public CategoryDTO(Category obj) {
		this.id = obj.getId();
		this.description = obj.getDescription();
	}
}
