package hr.pgalina.chain_reaction.domain.feature.order.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@RequiredArgsConstructor
public class OrderDto implements Serializable {

    private Long idOrder;

    private List<OrderProductDto> products;

    private DeliveryTypeDto deliveryType;

    private String estimatedDelivery;

    private AddressDto address;

    private OrderStatusDto status;

    private Double total;
}
