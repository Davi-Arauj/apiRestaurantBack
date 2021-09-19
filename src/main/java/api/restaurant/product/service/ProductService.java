package api.restaurant.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.restaurant.product.dto.ProductDTO;
import api.restaurant.product.dto.response.ProductResponseDTO;
import api.restaurant.product.entity.Product;
import api.restaurant.product.mapper.ProductMap;
import api.restaurant.product.mapper.ProductMapImp;
import api.restaurant.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductService {

	
	private  ProductRepository proRepository;

	private  ProductMapImp productMapper;
	
    
    //Criando uma nova Pessoa.
    public ProductResponseDTO createProduct(ProductDTO productDTO) {
        Product savedProduct = proRepository.save(productMapper.toModel(productDTO));
        return createMessageResponse(savedProduct.getId(), "Created person with ID ");
    }
   
    
    //Metodo criar menssagem de resposta.
    private ProductResponseDTO createMessageResponse(Long id, String message) {
        return ProductResponseDTO
        		.builder()
                .message(message + id)
                .build();
    }

}
