package api.restaurant.mapper;

import org.springframework.stereotype.Component;

import api.restaurant.dto.ClientDTO;
import api.restaurant.entity.Client;

@Component
public class ClientMapping implements ClientMapper{

	@Override
	public Client toModel(ClientDTO clientDTO) {
		// TODO Auto-generated method stub
		Client cat = new Client();
		cat.setId(clientDTO.getId());
		cat.setName(clientDTO.getName());
		return cat;
	}

	@Override
	public ClientDTO toDTO(Client cli) {
		// TODO Auto-generated method stub
		ClientDTO catDto = new ClientDTO();
		catDto.setId(cli.getId());
		catDto.setName(cli.getName());
		return catDto;
	}

}
