package api.restaurant.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sale.entity.Sale;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {

	private Long id;
	private String description;
	private double price;
	private int amount;
	private Sale sale;
}
