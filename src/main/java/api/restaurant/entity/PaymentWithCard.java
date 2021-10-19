package api.restaurant.entity;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;

import api.restaurant.entity.enums.Paymentstate;

@Entity
@JsonTypeName("paymentWithCard")
public class PaymentWithCard extends Payment{

	private static final long serialVersionUID = 1L;

	private Integer numberOfInstallments;
	
	public PaymentWithCard() {}

	public PaymentWithCard(Long id, Paymentstate state, Pedido pedido,Integer numberOfInstallments) {
		super(id, state, pedido);
		this.numberOfInstallments = numberOfInstallments;
	}

	public Integer getNumberOfInstallments() {
		return numberOfInstallments;
	}

	public void setNumberOfInstallments(Integer numberOfInstallments) {
		this.numberOfInstallments = numberOfInstallments;
	}
	
}
