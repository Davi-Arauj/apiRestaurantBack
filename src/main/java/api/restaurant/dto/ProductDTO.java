package api.restaurant.dto;

import api.restaurant.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ProductDTO {

	private Long id;
	private String nome;
	private double price;
	
	public ProductDTO(Product obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.price = obj.getPrice();
	
		
	}

	public ProductDTO() {
		
	}
}
