package hr.pgalina.chain_reaction.domain.features.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class ProductDto implements Serializable {

    private String name;

    private String description;

    private String model;

    private Integer assistSpeed;

    private Integer batteryRange;

    private Double chargingTime;

    private Double weight;

    private Double price;

    private ProductColorDto color;

    private Integer availableQuantity;

    private String imagePath;

    private ProductTypeDto type;
}
