package hr.pgalina.chain_reaction.domain.features.product.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class ProductTypeDto implements Serializable {

    private Short idProductType;

    private String value;
}
