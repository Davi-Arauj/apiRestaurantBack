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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import api.restaurant.dto.SaleDTO;
import api.restaurant.dto.response.SaleResponseDTO;
import api.restaurant.exception.SaleNotFoundException;
import api.restaurant.service.SaleService;

@RestController
public class SaleResource {

	 @Autowired
	 private SaleService saleService;
	 
	    @PostMapping
	    @ResponseStatus(HttpStatus.CREATED)
	    public SaleResponseDTO createSale(@RequestBody @Valid SaleDTO saleDTO) {
	        return saleService.createSale(saleDTO);
	    }
	    
	    @GetMapping
	    public List<SaleDTO> listAll() {
	        return saleService.listAll();
	    }
	    
	    @GetMapping("/{id}")
	    public SaleDTO findById(@PathVariable Long id) throws SaleNotFoundException {
	        return saleService.findById(id);
	    }
	    @PutMapping("/{id}")
	    public SaleResponseDTO updateById(@PathVariable Long id, @RequestBody @Valid SaleDTO saleDTO) throws SaleNotFoundException {
	        return saleService.updateById(id, saleDTO);
	    }
	    @DeleteMapping("/{id}")
	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    public void deleteById(@PathVariable Long id) throws SaleNotFoundException {
	        saleService.delete(id);
	    }
}
