package api.restaurant.mapper;

import org.springframework.stereotype.Component;

import api.restaurant.dto.ProductDTO;
import api.restaurant.entity.Product;

@Component
public class ProductMapImp implements ProductMap{

	@Override
	public Product toModel(ProductDTO productDTO) {
		// TODO Auto-generated method stub
		Product pro = new Product();
		pro.setId(productDTO.getId());
		pro.setAmount(productDTO.getAmount());
		pro.setDescription(productDTO.getDescription());
		pro.setPrice(productDTO.getPrice());
		return pro;
	}

	@Override
	public ProductDTO toDTO(Product product) {
		// TODO Auto-generated method stub
		ProductDTO proDto = new ProductDTO();
		proDto.setId(product.getId());
		proDto.setAmount(product.getAmount());
		proDto.setDescription(product.getDescription());
		proDto.setPrice(product.getPrice());
		return proDto;
	}

}
