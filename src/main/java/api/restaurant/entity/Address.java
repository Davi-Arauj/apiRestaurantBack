package api.restaurant.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Data
@Entity
public class Address implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String publicPlace;
	private String number;
	private String complement;
	private String district;
	private String cep;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name ="client_id")
	private Client client;
	@ManyToOne
	@JoinColumn(name ="city_id")
	private City city;
	
	public Address() {}

	public Address(Integer id, String publicPlace, String number, String complement, String district, String cep,
			Client client, City city) {
		super();
		this.id = id;
		this.publicPlace = publicPlace;
		this.number = number;
		this.complement = complement;
		this.district = district;
		this.cep = cep;
		this.client = client;
		this.city = city;
	}

}
