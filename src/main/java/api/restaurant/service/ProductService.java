package api.restaurant.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import api.restaurant.dto.ProductDTO;
import api.restaurant.dto.response.ProductResponseDTO;
import api.restaurant.entity.Category;
import api.restaurant.entity.Product;
import api.restaurant.mapper.ProductMaping;
import api.restaurant.repository.CategoryRepository;
import api.restaurant.repository.ProductRepository;
import api.restaurant.service.exception.ObjectNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductService {

	private CategoryRepository caRepository;
	private  ProductRepository proRepository;

	private  ProductMaping productMapper;
	
    
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
    public Product findById(Long id) throws ObjectNotFoundException {
    	return verifyIfExists(id);
    }
    
    //Metodo verificar se produto existe para nos auxiliar no desenvolvimento.
    private Product verifyIfExists(Long id) throws ObjectNotFoundException {
        return proRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Object not found"));
    }
    
    //Atualizando um produto, primeiro verifica se existe e assim atualiza, se n??o existir ele ser?? criado.
    public ProductResponseDTO updateById(Long id, ProductDTO productDTO) throws ObjectNotFoundException {
        verifyIfExists(id);
        Product productToUpdate = productMapper.toModel(productDTO);
        Product updatedProduct = proRepository.save(productToUpdate);
        return createMessageResponse(updatedProduct.getId(), "Updated product with ID ");
    }
    
    //Deletando um produto por o ID, mais antes verifica se ele existe.
    public void delete(Long id) throws ObjectNotFoundException {
        verifyIfExists(id);
        proRepository.deleteById(id);
    }
    
    public Page<Product> search(String description, List<Long> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Category> categories = caRepository.findAllById(ids);
		return proRepository.searchIdProductAndCategory(description, categories, pageRequest);	
	}
    
}
