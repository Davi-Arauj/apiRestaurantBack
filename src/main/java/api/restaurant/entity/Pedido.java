package api.restaurant.entity;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
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
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Entity
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date instant;
	
	@JsonManagedReference
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "request")
	private Payment payment;
	
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;
	@ManyToOne
	@JoinColumn(name = "delivery_address_id")
	private Address deliveryAddress;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "id.request")
	private Set<OrderedItem> itens = new HashSet<>();

	public Pedido() {
	}

	public Pedido(Long id, Date instant, Client client, Address deliveryAddress) {
		super();
		this.id = id;
		this.instant = instant;
		this.client = client;
		this.deliveryAddress = deliveryAddress;
	}

	public double getValorTotal() {
		double soma = 0.0;
		for (OrderedItem ip : itens) {
			soma = soma + ip.getSubTotal();
		}
		return soma;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		StringBuilder builder = new StringBuilder();
		builder.append("Pedido número: ");
		builder.append(getId());
		builder.append(", Instante: ");
		builder.append(sdf.format(getInstant()));
		builder.append(", Cliente: ");
		builder.append(getClient().getName());
		builder.append(", Situação do pagamento: ");
		builder.append(getPayment().getState().getDescription());
		builder.append("\nDetalhes:\n");
		for (OrderedItem ip : getItens()) {
			builder.append(ip.toString());
		}
		builder.append("Valor total: ");
		builder.append(nf.format(getValorTotal()));
		return builder.toString();
	}

}
