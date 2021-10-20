package api.restaurant.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import api.restaurant.entity.PaymentWithBoleto;

@Service
public class BoletoService {

	public void preencherPagamentoComBoleto(PaymentWithBoleto pagto, Date instanteDoPedido) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instanteDoPedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDateDue(cal.getTime());
	}
}