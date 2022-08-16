package hr.pgalina.chain_reaction.domain.features.order.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;

@Data
@RequiredArgsConstructor
public class ProductFilter implements Serializable {

    private String keyword;

    private ArrayList<String> brands;

    private ArrayList<Short> types;

    private ArrayList<Short> colors;

    private Double maxPrice;
}
