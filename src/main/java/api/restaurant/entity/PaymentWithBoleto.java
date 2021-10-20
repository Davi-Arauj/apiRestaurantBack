package api.restaurant.entity;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;

import api.restaurant.entity.enums.Paymentstate;
import lombok.Data;

@Data
@Entity
@JsonTypeName("paymentWithBoleto")
public class PaymentWithBoleto extends Payment{

	private static final long serialVersionUID = 1L;


	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dateDue;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date datePayment;
	
	public PaymentWithBoleto() {}

	public PaymentWithBoleto(Long id, Paymentstate state, Pedido pedido,Date dateDue,Date datePayment) {
		super(id, state, pedido);
		this.datePayment = datePayment;
		this.dateDue = dateDue;
		
	}


}
