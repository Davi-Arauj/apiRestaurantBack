package api.restaurant.resource;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import api.restaurant.dto.CategoryDTO;
import api.restaurant.dto.response.CategoryResponseDTO;
import api.restaurant.entity.Category;
import api.restaurant.service.CategoryService;
import api.restaurant.service.exception.ObjectNotFoundException;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryResource {


	 @Autowired
	 private CategoryService catService;
	 
		@PreAuthorize("hasAnyRole('ADMIN')")
	    @PostMapping
	    @ResponseStatus(HttpStatus.CREATED)
	    public CategoryResponseDTO create(@RequestBody @Valid CategoryDTO categoryDTO) {
	        return catService.createCategory(categoryDTO);
	    }
	    
	    @GetMapping
	    public ResponseEntity<List<CategoryDTO>> findAll() {
			List<Category> list = catService.findAll();
			List<CategoryDTO> listDto = list.stream().map(obj -> new CategoryDTO(obj)).collect(Collectors.toList());  
			return ResponseEntity.ok().body(listDto);
		}
	    
	    @GetMapping(value="/page")
	    public ResponseEntity<Page<CategoryDTO>> findPage(
				@RequestParam(value="page", defaultValue="0") Integer page, 
				@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
				@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
				@RequestParam(value="direction", defaultValue="ASC") String direction) {
			Page<Category> list = catService.findPage(page, linesPerPage, orderBy, direction);
			Page<CategoryDTO> listDto = list.map(obj -> new CategoryDTO(obj));  
			return ResponseEntity.ok().body(listDto);
		}
	    
	    @GetMapping("/{id}")
	    public CategoryDTO findById(@PathVariable Long id) throws ObjectNotFoundException {
	        return catService.findById(id);
	    }

	    @PreAuthorize("hasAnyRole('ADMIN')")
	    @PutMapping("/{id}")
	    public CategoryResponseDTO updateById(@PathVariable Long id, @RequestBody @Valid CategoryDTO categoryDTO) throws ObjectNotFoundException {
	        return catService.updateById(id, categoryDTO);
	    }
	    
		@PreAuthorize("hasAnyRole('ADMIN')")
	    @DeleteMapping("/{id}")
	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    public void deleteById(@PathVariable Long id) throws ObjectNotFoundException {
	        catService.delete(id);
	    }
	   
}
