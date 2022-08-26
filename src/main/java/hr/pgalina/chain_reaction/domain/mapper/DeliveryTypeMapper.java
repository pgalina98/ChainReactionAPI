package hr.pgalina.chain_reaction.domain.mapper;

import hr.pgalina.chain_reaction.domain.feature.order.dto.DeliveryTypeDto;
import hr.pgalina.chain_reaction.domain.feature.order.enumeration.DeliveryType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DeliveryTypeMapper {

    public DeliveryTypeDto mapToDto(DeliveryType deliveryType) {
        DeliveryTypeDto deliveryTypeDto = new DeliveryTypeDto();

        deliveryTypeDto.setIdDeliveryType(deliveryType.getIdDeliveryType());
        deliveryTypeDto.setValue(deliveryType.getValue());

        return deliveryTypeDto;
    }

    public List<DeliveryTypeDto> mapToDtos(List<DeliveryType> deliveryTypes) {
        return deliveryTypes
            .stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }
}
