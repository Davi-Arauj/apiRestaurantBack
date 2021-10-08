package sale.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import sale.dto.SaleDTO;
import sale.entity.Sale;

@Mapper(componentModel = "spring")
public interface SaleMapper {
	
	SaleMapper INSTANCE = Mappers.getMapper(SaleMapper.class);

	  
	    Sale toModel(SaleDTO saleDTO);
	    SaleDTO toDTO(Sale sale);
}
