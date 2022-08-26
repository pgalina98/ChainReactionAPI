package hr.pgalina.chain_reaction.domain.feature.order.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class OrderPage implements Serializable {

    private Long totalElements;

    private List<OrderDto> orders;
}
