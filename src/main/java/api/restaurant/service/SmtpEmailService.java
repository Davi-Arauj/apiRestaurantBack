package api.restaurant.service;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SmtpEmailService extends AbstractEmailService{

	private MailSender mailSender;
	
	private JavaMailSender javaMailSender;
	
	private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class); 

	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Envio de Email....");
		mailSender.send(msg);
		LOG.info("Email enviado!!!");
	}


	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		LOG.info("Enviando email...");
		javaMailSender.send(msg);
		LOG.info("Email enviado");
	}

}
