package hr.pgalina.chain_reaction.domain.mapper;

import hr.pgalina.chain_reaction.domain.feature.order.dto.OrderStatusDto;
import hr.pgalina.chain_reaction.domain.feature.order.enumeration.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderStatusMapper {

    public OrderStatusDto mapToDto(OrderStatus orderStatus) {
        OrderStatusDto orderStatusDto = new OrderStatusDto();

        orderStatusDto.setIdOrderStatus(orderStatus.getIdOrderStatus());
        orderStatusDto.setValue(orderStatus.getValue());

        return orderStatusDto;
    }
}
