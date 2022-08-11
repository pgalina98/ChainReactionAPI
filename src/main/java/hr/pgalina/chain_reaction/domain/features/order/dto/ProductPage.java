package hr.pgalina.chain_reaction.domain.features.order.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class ProductPage implements Serializable {

    private Long totalElements;

    private List<ProductDto> products;
}
