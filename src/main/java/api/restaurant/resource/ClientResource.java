package api.restaurant.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.restaurant.dto.ClientDTO;
import api.restaurant.dto.response.ClientResponseDTO;
import api.restaurant.entity.Client;
import api.restaurant.mapper.ClientMapping;
import api.restaurant.repository.ClientRepository;
import api.restaurant.service.exception.ObjectNotFoundException;

@RestController
@RequestMapping("/api/v1/client")
public class ClientResource {
	private ClientRepository clientRepository;

	private ClientMapping clientMaping;

	// Criando uma novo cliente.
	public ClientResponseDTO createClient(ClientDTO clientDTO) {
		Client savedClient = clientRepository.save(clientMaping.toModel(clientDTO));
		return createMessageResponse(savedClient.getId(), "Created Client with ID ");
	}

	// Metodo criar menssagem de resposta.
	private ClientResponseDTO createMessageResponse(Long id, String message) {
		return ClientResponseDTO.builder().message(message + id).build();
	}

	// Buscando todos os clientes e transformando em DTO.
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

	// Metodo verificar se uma categoria existe para nos auxiliar no
	// desenvolvimento.
	private Client verifyIfExists(Long id) throws ObjectNotFoundException {
		return clientRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Object not found"));
	}

	// Atualizando uma categoria, primeiro verifica se existe e assim atualiza, se
	// não existir ele será criado.
	public ClientResponseDTO updateById(Long id, ClientDTO clientDTO) throws ObjectNotFoundException {
		verifyIfExists(id);
		Client clientToUpdate = clientMaping.toModel(clientDTO);
		Client updatedClient = clientRepository.save(clientToUpdate);
		return createMessageResponse(updatedClient.getId(), "Updated Client with ID ");
	}

	// Deletando uma categoria por o ID, mais antes verifica se ele existe.
	public void delete(Long id) throws ObjectNotFoundException {
		verifyIfExists(id);
		clientRepository.deleteById(id);
	}


}
