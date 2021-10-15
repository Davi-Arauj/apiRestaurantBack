package api.restaurant.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Data
@Entity
public class Request implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date instant;
	
	
	@OneToOne(cascade=CascadeType.ALL , mappedBy="request")
	private Payment payment;
	
	
	@ManyToOne
	@JoinColumn(name="client_id")
	private Client client;
	@ManyToOne
	@JoinColumn(name="delivery_address_id")
	private Address deliveryAddress;
	
	public Request() {}

	public Request(Integer id, Date instant,Client client, Address deliveryAddress) {
		super();
		this.id = id;
		this.instant = instant;
		this.client = client;
		this.deliveryAddress = deliveryAddress;
	}

	
	@OneToMany(mappedBy = "id.request")
	private Set<OrderedItem> itens = new HashSet<>();
	public Integer getId() {
		return id;
	}

}
