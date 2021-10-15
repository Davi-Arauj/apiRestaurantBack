package api.restaurant.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import api.restaurant.dto.SaleDTO;
import api.restaurant.entity.Sale;

@Mapper(componentModel = "spring")
public interface SaleMapper {
	
	SaleMapper INSTANCE = Mappers.getMapper(SaleMapper.class);

	  
	    Sale toModel(SaleDTO saleDTO);
	    SaleDTO toDTO(Sale sale);
}
