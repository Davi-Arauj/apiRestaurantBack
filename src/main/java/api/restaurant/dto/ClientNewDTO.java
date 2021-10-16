package api.restaurant.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ClientNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private String email;
	private String CpfouCnpj;
	private Integer typeClient;
	
	
	private String publicPlace;
	private String number;
	private String complement;
	private String district;
	private String cep;
	
	private String telephone1;
	private String telephone2;
	private String telephone3;
	
	private Integer cityId;
	
	public ClientNewDTO() {}
}
