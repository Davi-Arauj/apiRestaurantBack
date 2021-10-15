package api.restaurant.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import api.restaurant.entity.enums.TypeClient;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Data
@Entity
public class Client implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String email;
	private String CpfouCnpj;
	private Integer typeClient;

	@OneToMany(mappedBy = "client")
	private List<Address> adresses = new ArrayList<>();

	@ElementCollection
	@CollectionTable(name = "telephone")
	private Set<String> telephones = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "client")
	private List<Request> requests = new ArrayList<>();

	public Client() {
	}

	public Client(Integer id, String name, String email, String cpfouCnpj, TypeClient typeClient) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.CpfouCnpj = cpfouCnpj;
		this.typeClient = typeClient.getCod();
	}


}
