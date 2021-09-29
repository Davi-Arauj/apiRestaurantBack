package api.restaurant.product.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.restaurant.product.dto.ProductDTO;
import api.restaurant.product.dto.response.ProductResponseDTO;
import api.restaurant.product.entity.Product;
import api.restaurant.product.exception.ProductNotFoundException;
import api.restaurant.product.mapper.ProductMapImp;
import api.restaurant.product.repository.ProductRepository;
import lombok.AllArgsConstructor;

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
    
  //Buscando todos os produtos e transformando em DTO.
    public List<ProductDTO> listAll() {
           List<Product> allProduct = proRepository.findAll();
    	   return allProduct.stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    
    //Buscando um produto por o ID, mais antes verifica se ele existe.
    public ProductDTO findById(Long id) throws ProductNotFoundException {
        Product product = verifyIfExists(id);
        return productMapper.toDTO(product);
    }
    
    //Metodo verificar se produto existe para nos auxiliar no desenvolvimento.
    private Product verifyIfExists(Long id) throws ProductNotFoundException {
        return proRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }
    
    //Atualizando um produto, primeiro verifica se existe e assim atualiza, se não existir ele será criado.
    public ProductResponseDTO updateById(Long id, ProductDTO productDTO) throws ProductNotFoundException {
        verifyIfExists(id);
        Product productToUpdate = productMapper.toModel(productDTO);
        Product updatedProduct = proRepository.save(productToUpdate);
        return createMessageResponse(updatedProduct.getId(), "Updated product with ID ");
    }
    
    //Deletando um produto por o ID, mais antes verifica se ele existe.
    public void delete(Long id) throws ProductNotFoundException {
        verifyIfExists(id);
        proRepository.deleteById(id);
    }

}
