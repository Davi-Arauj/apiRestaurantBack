package api.restaurant.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import api.restaurant.dto.ClientDTO;
import api.restaurant.entity.Client;


@Mapper(componentModel = "spring")
public interface ClientMapper {


	ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

	  
	    Client toModel(ClientDTO cliDTO);
	    ClientDTO toDTO(Client client);
	}

