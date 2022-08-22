package hr.pgalina.chain_reaction.domain.mapper;

import hr.pgalina.chain_reaction.domain.entity.DiscountCode;
import hr.pgalina.chain_reaction.domain.feature.discount_code.dto.DiscountCodeDto;
import org.springframework.stereotype.Component;

@Component
public class DiscountCodeMapper {

    public DiscountCodeDto mapToDto(DiscountCode discountCode) {
        DiscountCodeDto discountCodeDto = new DiscountCodeDto();

        discountCodeDto.setCode(discountCode.getCode());
        discountCodeDto.setDiscount(discountCode.getDiscount());
        discountCodeDto.setActiveFrom(discountCode.getActiveFrom());
        discountCodeDto.setActiveTo(discountCode.getActiveTo());

        return discountCodeDto;
    }
}
