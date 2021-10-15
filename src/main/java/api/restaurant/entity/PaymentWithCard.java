package api.restaurant.entity;

import api.restaurant.entity.enums.Paymentstate;

public class PaymentWithCard extends Payment{

	private static final long serialVersionUID = 1L;

	private Integer numberOfInstallments;
	
	public PaymentWithCard() {}

	public PaymentWithCard(Integer id, Paymentstate state, Request request,Integer numberOfInstallments) {
		super(id, state, request);
		this.numberOfInstallments = numberOfInstallments;
	}

	public Integer getNumberOfInstallments() {
		return numberOfInstallments;
	}

	public void setNumberOfInstallments(Integer numberOfInstallments) {
		this.numberOfInstallments = numberOfInstallments;
	}
	
}
