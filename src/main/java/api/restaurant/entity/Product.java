package api.restaurant.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	@Column
	private String nome;
	@Column
	private double price;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "PRODUCT_CATEGORY", joinColumns = @JoinColumn(name = "products_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private List<Category> categories = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "id.product")
	private Set<OrderedItem> itens = new HashSet<>();

	public Product(Long id, String description, double price) {
		super();
		this.id = id;
		this.nome = description;
		this.price = price;
	}

	public Product() {

	}
	
	@JsonIgnore
	public List<Pedido> getRequest() {
		List<Pedido> lista = new ArrayList<>();
		for (OrderedItem x : itens) {
			lista.add(x.getRequest());
		}
		return lista;
	}

}
