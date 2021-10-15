package api.restaurant.dto;

import api.restaurant.entity.Sale;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
