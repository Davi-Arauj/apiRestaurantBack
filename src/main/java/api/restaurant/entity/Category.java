package api.restaurant.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import api.restaurant.dto.ProductDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Data
@Entity
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
	
	@ManyToMany(mappedBy = "categories")
	private List<ProductDTO> products = new ArrayList<>();

	public Category() {

	}

	public Category(Long id, String name) {
		super();
		this.id = id;
		this.description = name;
	}
	
	
}
