package hr.pgalina.chain_reaction.domain.feature.order.dto;

import hr.pgalina.chain_reaction.domain.feature.product.dto.ProductDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class OrderProductDto implements Serializable {

    private ProductDto product;

    private Short quantity;
}
