package api.restaurant.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import api.restaurant.dto.ClientDTO;
import api.restaurant.dto.ClientNewDTO;
import api.restaurant.dto.response.ClientResponseDTO;
import api.restaurant.entity.Client;
import api.restaurant.service.ClientService;
import api.restaurant.service.exception.ObjectNotFoundException;
import lombok.AllArgsConstructor;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/api/v1/client")
public class ClientResource {

	private ClientService clientService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Void> createClient(@Valid @RequestBody ClientNewDTO clientNewDTO) {
		Client cli = clientService.fromDTO(clientNewDTO);
		clientService.insert(cli);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cli.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping
	public ResponseEntity<List<ClientDTO>> findAll() {
		List<Client> list = clientService.findAll();
		List<ClientDTO> listDto = list.stream().map(obj -> new ClientDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping(value="/page")
	public ResponseEntity<Page<ClientDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Client> list = clientService.findPage(page, linesPerPage, orderBy, direction);
		Page<ClientDTO> listDto = list.map(obj -> new ClientDTO(obj));  
		return ResponseEntity.ok().body(listDto);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Client> findById(@PathVariable Long id) {
		Client obj = clientService.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PutMapping("/{id}")
	public ClientResponseDTO updateById(@PathVariable Long id, @RequestBody @Valid ClientDTO clientDTO)
			throws ObjectNotFoundException {
		return clientService.updateById(clientDTO);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long id) throws ObjectNotFoundException {
		clientService.delete(id);
	}
	
	@PostMapping(value="/picture")
	public ResponseEntity<Void> uploadProfilePicture(@RequestParam(name="file") MultipartFile file) {
		URI uri = clientService.uploadProfilePicture(file);
		return ResponseEntity.created(uri).build();
	}

}
