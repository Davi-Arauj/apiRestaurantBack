package api.restaurant.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Data
@Entity
public class Sale implements Serializable{

	public Sale(Long id, double total, Long numberSale, Long numberTable, Date dateSale) {
		super();
		this.id = id;
		this.total = total;
		this.numberSale = numberSale;
		this.numberTable = numberTable;
		this.dateSale = dateSale;
	}
	public Sale() {
		
	}
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(mappedBy = "sale")
	private List<Product> products = new ArrayList<>();
	
	@Column
	private double total; 
	@Column
	private Long numberSale;
	@Column
	private Long numberTable;
	@Column
	private Date dateSale;
	
	
}
