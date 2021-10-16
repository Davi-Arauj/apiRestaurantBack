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

import api.restaurant.dto.CategoryDTO;
import api.restaurant.dto.response.CategoryResponseDTO;
import api.restaurant.exception.CategoryNotFoundException;
import api.restaurant.exception.ProductNotFoundException;
import api.restaurant.service.CategoryService;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryResource {


	 @Autowired
	 private CategoryService catService;
	 
	    @PostMapping
	    @ResponseStatus(HttpStatus.CREATED)
	    public CategoryResponseDTO create(@RequestBody @Valid CategoryDTO categoryDTO) {
	        return catService.createCategory(categoryDTO);
	    }
	    
	    @GetMapping
	    public List<CategoryDTO> listAll() {
	        return catService.listAll();
	    }
	    
	    @GetMapping("/{id}")
	    public CategoryDTO findById(@PathVariable Long id) throws CategoryNotFoundException {
	        return catService.findById(id);
	    }
	    @PutMapping("/{id}")
	    public CategoryResponseDTO updateById(@PathVariable Long id, @RequestBody @Valid CategoryDTO categoryDTO) throws CategoryNotFoundException {
	        return catService.updateById(id, categoryDTO);
	    }
	    @DeleteMapping("/{id}")
	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    public void deleteById(@PathVariable Long id) throws CategoryNotFoundException {
	        catService.delete(id);
	    }
}
