package api.restaurant.entity;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import api.restaurant.entity.enums.Paymentstate;


@Entity
public class PaymentWithBoleto extends Payment{

	private static final long serialVersionUID = 1L;


	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dateDue;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date datePayment;
	
	public PaymentWithBoleto() {}

	public PaymentWithBoleto(Integer id, Paymentstate state, Request request,Date dateDue,Date datePayment) {
		super(id, state, request);
		this.datePayment = datePayment;
		this.dateDue = dateDue;
		
	}

	public Date getDateDue() {
		return dateDue;
	}
}
