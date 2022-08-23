package hr.pgalina.chain_reaction.domain.feature.order.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class DeliveryTypeDto implements Serializable {

    private Short idDeliveryType;

    private String value;
}
