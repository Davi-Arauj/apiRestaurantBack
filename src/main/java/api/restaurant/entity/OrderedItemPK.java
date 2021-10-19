package api.restaurant.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Data
@Embeddable
public class OrderedItemPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "request_id")
	private Pedido request;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	

}
