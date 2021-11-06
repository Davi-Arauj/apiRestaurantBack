package api.restaurant.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import api.restaurant.entity.enums.Profile;
import api.restaurant.entity.enums.TypeClient;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
public class Client implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	private String name;
	private String email;
	private String cpfOuCnpj;
	private Integer typeClient;
	
	@JsonIgnore
	private String password;

	@OneToMany(mappedBy = "client", cascade=CascadeType.ALL)
	private List<Address> adresses = new ArrayList<>();

	@ElementCollection
	@CollectionTable(name = "telephone")
	private Set<String> telephones = new HashSet<>();
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name = "profiles")
	private Set<Integer> profiles = new HashSet<>();

	@JsonBackReference
	@OneToMany(mappedBy = "client")
	private List<Pedido> pedidos = new ArrayList<>();

	public Client() {
		addPerfil(Profile.CLIENT);

	}

	public Client(Long id, String name, String email, String cpfouCnpj, TypeClient typeClient, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.cpfOuCnpj = cpfouCnpj;
		this.typeClient = (typeClient==null) ? null : typeClient.getCod();
		this.password = password;
		addPerfil(Profile.CLIENT);

	}

	public Set<Profile> getPerfis() {
		return profiles.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());
	}

	public void addPerfil(Profile perfil) {
		profiles.add(perfil.getCod());
	}

}
