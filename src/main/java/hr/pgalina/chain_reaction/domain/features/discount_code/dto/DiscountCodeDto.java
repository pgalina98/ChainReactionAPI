package hr.pgalina.chain_reaction.domain.features.discount_code.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class DiscountCodeDto implements Serializable {

    private String code;

    private Double discount;

    private LocalDate activeFrom;

    private LocalDate activeTo;
}
