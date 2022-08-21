package hr.pgalina.chain_reaction.domain.features.discount_code.service;

import hr.pgalina.chain_reaction.domain.features.discount_code.dto.DiscountCodeDto;

public interface DiscountCodeService {

    DiscountCodeDto validateDiscountCode(String code);
}
