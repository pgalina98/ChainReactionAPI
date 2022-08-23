package hr.pgalina.chain_reaction.domain.feature.cart.dto;

import hr.pgalina.chain_reaction.domain.feature.product.dto.ProductDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class CartItemDto extends ProductDto implements Serializable {

    private Integer quantity;
}
