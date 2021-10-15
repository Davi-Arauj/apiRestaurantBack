package api.restaurant.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

import api.restaurant.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleDTO {
	private Long id;
	private List<Product> products;
	private double total; 
	private Long numberSale;
	private Long numberTable;
	private Date dateSale;
	
}
