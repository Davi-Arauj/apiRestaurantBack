package api.restaurant.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import api.restaurant.dto.ClientDTO;
import api.restaurant.dto.ClientNewDTO;
import api.restaurant.dto.response.ClientResponseDTO;
import api.restaurant.entity.Address;
import api.restaurant.entity.City;
import api.restaurant.entity.Client;
import api.restaurant.entity.enums.TypeClient;
import api.restaurant.repository.AddressRepository;
import api.restaurant.repository.ClientRepository;
import api.restaurant.service.exception.DataIntegrityException;
import api.restaurant.service.exception.ObjectNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClientService {

	private ClientRepository clientRepository;
	
	private AddressRepository addressRepository;
	
	private BCryptPasswordEncoder pe;

	// Criando um novo cliente.
	@Transactional
	public Client insert(Client obj) {
		obj.setId(null);
		obj = clientRepository.save(obj);
		addressRepository.saveAll(obj.getAdresses());
		return obj;
	}

	// Metodo criar menssagem de resposta.
	private ClientResponseDTO createMessageResponse(Long id, String message) {
		return ClientResponseDTO.builder().message(message + id).build();
	}

	// Buscando todos os clientes.
	public List<Client> findAll() {
		return clientRepository.findAll();
	}

	// Buscando todos os clientes de forma paginada
	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clientRepository.findAll(pageRequest);
	}

	// Buscando um cliente por o ID
	public Client findById(Long id) throws ObjectNotFoundException {
		Optional<Client> obj = clientRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Client.class.getName()));
	}

	// Metodo verificar se um objeto existe 	
	private Client verifyIfExists(Long id) throws ObjectNotFoundException {
		return clientRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Object not found"));
	}

	// Atualizando um cliente, primeiro verifica se existe e assim atualiza,
	public ClientResponseDTO updateById(ClientDTO clientDTO) throws ObjectNotFoundException {
		Client clientToUpdate = verifyIfExists(clientDTO.getId());// verifica no banco a existência do objeto
		updateData(clientToUpdate, clientDTO);
		clientRepository.save(clientToUpdate);
		return createMessageResponse(clientToUpdate.getId(), "Updated Client with ID ");
	}

	// Deletando um cliente por o ID, mais antes verifica se ele existe.
	public void delete(Long id) throws ObjectNotFoundException {
		verifyIfExists(id);
		try {
			clientRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Cannot delete because there are related entities");		}
	}
	//Metodo para auxiliar na atualização
	private Client updateData(Client updatedClient, ClientDTO clientdto) {
		updatedClient.setName(clientdto.getName());
		updatedClient.setEmail(clientdto.getEmail());
		return updatedClient;
	}

	//Transformando um objetoDTO em objeto
	public Client fromDTO(ClientDTO objDto) {
		return new Client(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null,null);
	}

	//Transformando um objeto em objetoDTO
	public Client fromDTO(ClientNewDTO objDto) {
		Client cli = new Client(null, objDto.getName(), objDto.getEmail(), objDto.getCpfOuCnpj(),
				TypeClient.toEnum(objDto.getTypeClient()),pe.encode(objDto.getPassword()));
		City cid = new City(objDto.getCityId(), null, null);
		Address end = new Address(null, objDto.getPublicPlace(), objDto.getNumber(), objDto.getComplement(),
				objDto.getDistrict(), objDto.getCep(), cli, cid);
		cli.getAdresses().add(end);
		cli.getTelephones().add(objDto.getTelephone1());
		if (objDto.getTelephone2() != null) {
			cli.getTelephones().add(objDto.getTelephone2());
		}
		if (objDto.getTelephone3() != null) {
			cli.getTelephones().add(objDto.getTelephone3());
		}
		return cli;
	}
}
