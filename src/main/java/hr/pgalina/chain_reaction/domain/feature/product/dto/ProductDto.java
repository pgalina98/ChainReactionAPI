package hr.pgalina.chain_reaction.domain.feature.product.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class ProductDto implements Serializable {

    private Long idProduct;

    private String name;

    private String description;

    private String model;

    private Integer assistSpeed;

    private Integer batteryRange;

    private Double chargingTime;

    private Double weight;

    private Double price;

    private ProductColorDto color;

    private Short availableQuantity;

    private Boolean forRent;

    private Double rentPricePerHour;

    private String imagePath;

    private ProductTypeDto type;
}
