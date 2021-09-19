package api.restaurant.product.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import api.restaurant.product.dto.ProductDTO;
import api.restaurant.product.dto.response.ProductResponseDTO;
import api.restaurant.product.service.ProductService;
import lombok.AllArgsConstructor;

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


}
