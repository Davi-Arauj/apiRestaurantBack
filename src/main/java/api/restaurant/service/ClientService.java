package api.restaurant.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import api.restaurant.dto.ClientDTO;
import api.restaurant.dto.ClientNewDTO;
import api.restaurant.dto.response.ClientResponseDTO;
import api.restaurant.entity.Address;
import api.restaurant.entity.City;
import api.restaurant.entity.Client;
import api.restaurant.entity.enums.TypeClient;
import api.restaurant.mapper.ClientMapping;
import api.restaurant.repository.ClientRepository;
import api.restaurant.service.exception.ObjectNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClientService {

	private ClientRepository clientRepository;

	private ClientMapping clientMaping;

	// Criando um novo cliente.
	@Transactional
	public Client insert(Client obj) {
		obj.setId(null);
		obj = clientRepository.save(obj);
		return obj;
	}

	// Metodo criar menssagem de resposta.
	private ClientResponseDTO createMessageResponse(Long id, String message) {
		return ClientResponseDTO.builder().message(message + id).build();
	}

	// Buscando todos as categorias e transformando em DTO.
	public List<ClientDTO> listAll() {
		List<Client> allClient = clientRepository.findAll();
		return allClient.stream().map(clientMaping::toDTO).collect(Collectors.toList());
	}

	// Buscando todos as categorias de forma paginada
	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clientRepository.findAll(pageRequest);
	}

	// Buscando uma categoria por o ID, mais antes verifica se ele existe.
	public ClientDTO findById(Long id) throws ObjectNotFoundException {
		Client client = verifyIfExists(id);
		return clientMaping.toDTO(client);
	}

	// Metodo verificar se um objeto existe para nos auxiliar no
	// desenvolvimento.
	private Client verifyIfExists(Long id) throws ObjectNotFoundException {
		return clientRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Object not found"));
	}

	// Atualizando uma categoria, primeiro verifica se existe e assim atualiza,
	// Se não existir ele será criado.
	public ClientResponseDTO updateById(ClientDTO clientDTO) throws ObjectNotFoundException {
		Client clientToUpdate = verifyIfExists(clientDTO.getId());// verifica no banco a existência do objeto
		updateData(clientToUpdate, clientDTO);
		clientRepository.save(clientToUpdate);
		return createMessageResponse(clientToUpdate.getId(), "Updated Client with ID ");
	}

	// Deletando uma categoria por o ID, mais antes verifica se ele existe.
	public void delete(Long id) throws ObjectNotFoundException {
		verifyIfExists(id);
		clientRepository.deleteById(id);
	}

	private Client updateData(Client updatedClient, ClientDTO clientdto) {
		updatedClient.setName(clientdto.getName());
		updatedClient.setEmail(clientdto.getEmail());
		return updatedClient;
	}

	public Client fromDTO(ClientDTO objDto) {
		return new Client(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null);
	}

	public Client fromDTO(ClientNewDTO objDto) {
		Client cli = new Client(null, objDto.getName(), objDto.getEmail(), objDto.getCpfouCnpj(),
				TypeClient.toEnum(objDto.getTypeClient()));
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
