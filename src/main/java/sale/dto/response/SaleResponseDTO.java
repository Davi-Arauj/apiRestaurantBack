package sale.dto.response;

import api.restaurant.product.dto.response.ProductResponseDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SaleResponseDTO {
    private String message;

}
