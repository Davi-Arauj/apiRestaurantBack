package api.restaurant.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Data
@Entity
public class OrderedItem implements Serializable{

	private static final long serialVersionUID = 1L;
	@JsonIgnore
	@EmbeddedId
	private OrderedItemPK id = new OrderedItemPK();
	private Integer amount;
	private Double price;
	private Double discount;
	
	public OrderedItem() {}

	public OrderedItem(Request request,Product product,Double price,Integer amount, Double discount) {
		super();
		id.setRequest(request);
		id.setProduct(product);
		this.amount = amount;
		this.price = price;
		this.discount = discount;
	}
	
	@JsonIgnore
	public Request getRequest() {
		return id.getRequest();
	}
	
	public Product getProduct() {
		return id.getProduct();
	}

	public OrderedItemPK getId() {
		return id;
	}

}
