package api.restaurant.entity;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Data
@Entity
public class OrderedItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
	private OrderedItemPK id = new OrderedItemPK();
	private Integer amount;
	private Double price;
	private Double discount;

	public OrderedItem() {
	}

	public OrderedItem(Pedido pedido, Product product, Double price, Integer amount, Double discount) {
		super();
		id.setRequest(pedido);
		id.setProduct(product);
		this.amount = amount;
		this.price = price;
		this.discount = discount;
	}

	@JsonIgnore
	public Pedido getRequest() {
		return id.getRequest();
	}

	public void setPedido(Pedido pedido) {
		id.setRequest(pedido);
	}
	
	public Product getProduct() {
		return id.getProduct();
	}

	public void setProduct(Product product) {
		id.setProduct(product);
	}
	
	@JsonIgnore 
	public OrderedItemPK getId() {
		return id;
	}

	public void setId(OrderedItemPK id) {
		this.id = id;
	}

	public double getSubTotal() {
		return (price - discount) * amount;
	}
	
	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		StringBuilder builder = new StringBuilder();
		builder.append(getProduct().getNome());
		builder.append(", Qte: ");
		builder.append(getAmount());
		builder.append(", Preço unitário: ");
		builder.append(nf.format(getPrice()));
		builder.append(", Subtotal: ");
		builder.append(nf.format(getSubTotal()));
		builder.append("\n");
		return builder.toString();
	}

}
