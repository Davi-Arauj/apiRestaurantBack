package api.restaurant.mapper;

import org.springframework.stereotype.Component;

import api.restaurant.dto.SaleDTO;
import api.restaurant.entity.Sale;

@Component
public class SaleMapping implements SaleMapper{

	@Override
	public SaleDTO toDTO(Sale sale) {
		SaleDTO saleDto = new SaleDTO();
		saleDto.setId(sale.getId());
		saleDto.setDateSale(sale.getDateSale());
		saleDto.setNumberSale(sale.getNumberSale());
		saleDto.setNumberTable(sale.getNumberTable());
		saleDto.setTotal(sale.getTotal());
		saleDto.setProducts(sale.getProducts());
		return saleDto;
	}
	@Override
	public Sale toModel(SaleDTO saleDTO) {

		Sale sale = new Sale();
		sale.setId(saleDTO.getId());
		sale.setDateSale(saleDTO.getDateSale());
		sale.setNumberSale(saleDTO.getNumberSale());
		sale.setNumberTable(saleDTO.getNumberTable());
		sale.setTotal(saleDTO.getTotal());
		sale.setProducts(saleDTO.getProducts());
		return sale;
	}
}
