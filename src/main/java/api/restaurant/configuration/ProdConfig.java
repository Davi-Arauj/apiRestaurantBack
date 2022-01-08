package api.restaurant.configuration;

import java.text.ParseException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import api.restaurant.service.DBService;
import api.restaurant.service.EmailService;
import api.restaurant.service.SmtpEmailService;

@Configuration
@Profile("prod")
public class ProdConfig {

	@Autowired
	private DBService dbService;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;

	@Value("${spring.datasource.url}")
	private String dbUrl;

	@Bean
	public DataSource dataSource() {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(dbUrl);
		return new HikariDataSource(config);
	}

	@Bean
	public boolean instantiateDatabase() throws ParseException {

		if (!"create".equals(strategy)) {
			return false;
		}

		dbService.instantiateTestDatabase();
		return true;
	}

	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();

	}
}