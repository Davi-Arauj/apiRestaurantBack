package api.restaurant.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import api.restaurant.entity.enums.Paymentstate;
import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Payment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	private Integer state;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name="request_id")
	@MapsId
	private Pedido request;

	public Payment() {
	}

	public Payment(Long id, Paymentstate state, Pedido pedido) {
		super();
		this.id = id;
		this.state = state.getCod();
		this.request = pedido;
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
		Payment other = (Payment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

}
