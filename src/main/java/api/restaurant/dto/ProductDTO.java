package api.restaurant.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import api.restaurant.entity.Category;
import api.restaurant.entity.OrderedItem;
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
	private List<Category> categories = new ArrayList<>();
	private Set<OrderedItem> itens = new HashSet<>();
	
	public ProductDTO(Product obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.price = obj.getPrice();
	
		
	}
	
	public ProductDTO(Long id, String description, double price) {
		super();
		this.id = id;
		this.nome = description;
		this.price = price;
	}

	public ProductDTO() {
		
	}

	 
}
