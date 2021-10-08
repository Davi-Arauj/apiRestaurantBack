package sale.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import sale.dto.SaleDTO;
import sale.dto.response.SaleResponseDTO;
import sale.entity.Sale;
import sale.exception.SaleNotFoundException;
import sale.mapper.SaleMapping;
import sale.repository.SaleRepository;

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
      public SaleDTO findById(Long id) throws SaleNotFoundException {
          Sale sale = verifyIfExists(id);
          return saleMapper.toDTO(sale);
      }
      
      //Atualizando uma venda, primeiro verifica se ela existe e assim atualiza, se não existir ela será criada.
      public SaleResponseDTO updateById(Long id, SaleDTO saleDto) throws SaleNotFoundException {
          verifyIfExists(id);
          Sale saleToUpdate = saleMapper.toModel(saleDto);
          Sale updatedSale = saleRepository.save(saleToUpdate);
          return createMessageResponse(updatedSale.getId(), "Updated sale with ID ");
      }
      
      //Deletando uma Venda por o ID, mais antes verifica se ela existe.
      public void delete(Long id) throws SaleNotFoundException {
          verifyIfExists(id);
          saleRepository.deleteById(id);
      }
    
    

      //Metodo verificar se uma venda existe para nos auxiliar no desenvolvimento.
      private Sale verifyIfExists(Long id) throws SaleNotFoundException {
          return saleRepository.findById(id)
                  .orElseThrow(() -> new SaleNotFoundException(id));
      }
      
    //Metodo criar menssagem de resposta.
    private SaleResponseDTO createMessageResponse(Long id, String message) {
        return SaleResponseDTO
        		.builder()
                .message(message + id)
                .build();
    }
    
    
}
