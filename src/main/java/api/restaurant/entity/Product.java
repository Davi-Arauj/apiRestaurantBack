package api.restaurant.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Data
@Entity
public class Product implements Serializable{

	private static final long serialVersionUID = 1L; 

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    @Column
	private String description;
    @Column
	private double price;
    @Column
    private Integer amount;
   
    @JsonIgnore
	@ManyToMany
	@JoinTable(name = "PRODUCT_CATEGORY",
	joinColumns = @JoinColumn(name= "products_id"),
	inverseJoinColumns = @JoinColumn(name="category_id")
	)
	private List<Category> categories = new ArrayList<>();
	
    
	@ManyToOne
	@JoinColumn(name="sale_id")
    private Sale sale;

	public Product(Long id, String description, double price, int amount, Sale sale) {
		super();
		this.id = id;
		this.description = description;
		this.price = price;
		this.amount = amount;
		this.sale = sale;
	}
	public Product() {
		
	}
	
	
	
	
}
