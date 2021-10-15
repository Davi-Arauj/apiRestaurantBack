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
	private Integer id;
	private Integer state;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name="request_id")
	@MapsId
	private Request request;

	public Payment() {
	}

	public Payment(Integer id, Paymentstate state, Request request) {
		super();
		this.id = id;
		this.state = state.getCod();
		this.request = request;
	}

}
