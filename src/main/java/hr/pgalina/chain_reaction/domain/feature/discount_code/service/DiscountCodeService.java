package hr.pgalina.chain_reaction.domain.feature.discount_code.service;

import hr.pgalina.chain_reaction.domain.feature.discount_code.dto.DiscountCodeDto;

public interface DiscountCodeService {

    DiscountCodeDto validateDiscountCode(String code);
}
