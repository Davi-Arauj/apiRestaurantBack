package api.restaurant.product.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import api.restaurant.product.dto.ProductDTO;
import api.restaurant.product.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMap {

  ProductMap INSTANCE = Mappers.getMapper(ProductMap.class);

  
    Product toModel(ProductDTO productDTO);
    ProductDTO toDTO(Product product);
}
