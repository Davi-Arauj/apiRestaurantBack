package api.restaurant.service;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import api.restaurant.entity.Client;
import api.restaurant.entity.Pedido;

public interface EmailService {

	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendOrderConfirmationHtmlEmail(Pedido obj);
	
	void sendHtmlEmail(MimeMessage msg);
	
	void sendNewPasswordEmail(Client cliente, String newPass);
}