package api.restaurant.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.restaurant.dto.SaleDTO;
import api.restaurant.dto.response.SaleResponseDTO;
import api.restaurant.entity.Sale;
import api.restaurant.mapper.SaleMapping;
import api.restaurant.repository.SaleRepository;
import api.restaurant.service.exception.ObjectNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SaleService {
	
	private SaleRepository saleRepository;
	
	private SaleMapping saleMapper;

	//Criando uma nova Venda.
    public SaleResponseDTO createSale(SaleDTO saleDTO) {
        Sale savedSale = saleRepository.save(saleMapper.toModel(saleDTO));
        return createMessageResponse(savedSale.getId(), "Created sale with ID ");
    }
    
    //Buscando todos as vendas e transformando em DTO.
      public List<SaleDTO> listAll() {
             List<Sale> allSale = saleRepository.findAll();
      	   return allSale.stream()
                  .map(saleMapper::toDTO)
                  .collect(Collectors.toList());
      }
    
    //Buscando uma Venda por o ID, mais antes verifica se ela existe.
      public SaleDTO findById(Long id) throws ObjectNotFoundException {
          Sale sale = verifyIfExists(id);
          return saleMapper.toDTO(sale);
      }
      
      //Atualizando uma venda, primeiro verifica se ela existe e assim atualiza, se não existir ela será criada.
      public SaleResponseDTO updateById(Long id, SaleDTO saleDto) throws ObjectNotFoundException {
          verifyIfExists(id);
          Sale saleToUpdate = saleMapper.toModel(saleDto);
          Sale updatedSale = saleRepository.save(saleToUpdate);
          return createMessageResponse(updatedSale.getId(), "Updated sale with ID ");
      }
      
      //Deletando uma Venda por o ID, mais antes verifica se ela existe.
      public void delete(Long id) throws ObjectNotFoundException {
          verifyIfExists(id);
          saleRepository.deleteById(id);
      }
    
    

      //Metodo verificar se uma venda existe para nos auxiliar no desenvolvimento.
      private Sale verifyIfExists(Long id) throws ObjectNotFoundException {
          return saleRepository.findById(id)
                  .orElseThrow(() -> new ObjectNotFoundException("Object not found"));
      }
      
    //Metodo criar menssagem de resposta.
    private SaleResponseDTO createMessageResponse(Long id, String message) {
        return SaleResponseDTO
        		.builder()
                .message(message + id)
                .build();
    }
    
    
}
