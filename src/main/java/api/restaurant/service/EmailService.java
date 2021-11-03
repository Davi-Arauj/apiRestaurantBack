package api.restaurant.service;

import org.springframework.mail.SimpleMailMessage;

import api.restaurant.entity.Pedido;

public interface EmailService {

	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
