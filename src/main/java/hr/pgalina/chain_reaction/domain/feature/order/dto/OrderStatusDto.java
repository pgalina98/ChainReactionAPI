package hr.pgalina.chain_reaction.domain.feature.order.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class OrderStatusDto implements Serializable {

    private Short idOrderStatus;

    private String value;
}
