package api.restaurant.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import api.restaurant.dto.ProductDTO;
import api.restaurant.dto.response.ProductResponseDTO;
import api.restaurant.service.ProductService;
import api.restaurant.service.exception.ObjectNotFoundException;

@RestController
@RequestMapping("/api/v1/product")
public class ProductResource {
	
	 @Autowired
	 private ProductService productService;
	 
	    @PostMapping
	    @ResponseStatus(HttpStatus.CREATED)
	    public ProductResponseDTO createProduct(@RequestBody @Valid ProductDTO productDTO) {
	        return productService.createProduct(productDTO);
	    }
	    
	    @GetMapping
	    public List<ProductDTO> listAll() {
	        return productService.listAll();
	    }
	    
	    @GetMapping("/{id}")
	    public ProductDTO findById(@PathVariable Long id) throws ObjectNotFoundException {
	        return productService.findById(id);
	    }
	    @PutMapping("/{id}")
	    public ProductResponseDTO updateById(@PathVariable Long id, @RequestBody @Valid ProductDTO productDTO) throws ObjectNotFoundException {
	        return productService.updateById(id, productDTO);
	    }
	    @DeleteMapping("/{id}")
	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    public void deleteById(@PathVariable Long id) throws ObjectNotFoundException {
	        productService.delete(id);
	    }

}
