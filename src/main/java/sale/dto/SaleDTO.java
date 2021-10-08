package sale.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

import api.restaurant.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
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
