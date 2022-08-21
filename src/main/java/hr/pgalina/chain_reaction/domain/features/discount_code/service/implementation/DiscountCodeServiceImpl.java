package hr.pgalina.chain_reaction.domain.features.discount_code.service.implementation;

import hr.pgalina.chain_reaction.domain.entity.DiscountCode;
import hr.pgalina.chain_reaction.domain.exception.BadRequestException;
import hr.pgalina.chain_reaction.domain.exception.constant.ErrorTypeConstants;
import hr.pgalina.chain_reaction.domain.features.discount_code.service.DiscountCodeService;
import hr.pgalina.chain_reaction.domain.repository.DiscountCodeRepository;
import hr.pgalina.chain_reaction.domain.util.DateTimeUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static hr.pgalina.chain_reaction.domain.exception.constant.ExceptionMessages.DISCOUNT_CODE_DOES_NOT_EXIST;
import static hr.pgalina.chain_reaction.domain.exception.constant.ExceptionMessages.DISCOUNT_CODE_IS_NOT_VALID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DiscountCodeServiceImpl implements DiscountCodeService {

    private final DiscountCodeRepository discountCodeRepository;

    @Override
    public void validateDiscountCode(String code) {
        log.info("Entered validateDiscountCode in DiscountCodeServiceImpl with code {}.", code);

        DiscountCode discountCode = discountCodeRepository
            .findByCode(code)
            .orElseThrow(() -> new BadRequestException(ErrorTypeConstants.ERROR, HttpStatus.NOT_FOUND, DISCOUNT_CODE_DOES_NOT_EXIST));

        if (DateTimeUtils.isExpired(discountCode.getActiveTo())) {
            throw new BadRequestException(ErrorTypeConstants.ERROR, HttpStatus.BAD_REQUEST, DISCOUNT_CODE_IS_NOT_VALID);
        }
    }
}
