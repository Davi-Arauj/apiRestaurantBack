package api.restaurant.product.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sale.entity.Sale;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Product implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; 

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    @Column
	private String description;
    @Column
	private double price;
    @Column
    private int amount;
   
    @Autowired
    @ManyToOne
	@JoinColumn(name="sale_id")
    private Sale sale;
	
}
