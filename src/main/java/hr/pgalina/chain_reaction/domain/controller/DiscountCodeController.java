package hr.pgalina.chain_reaction.domain.controller;

import hr.pgalina.chain_reaction.domain.features.discount_code.dto.DiscountCodeDto;
import hr.pgalina.chain_reaction.domain.features.discount_code.service.DiscountCodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/discount-codes")
@RequiredArgsConstructor
public class DiscountCodeController {

    private final DiscountCodeService discountCodeService;

    @PostMapping("/{code}/validate")
    public ResponseEntity<DiscountCodeDto> validateDiscountCode(@PathVariable String code) {
        log.info("Entered '/api/discount-codes/{code}/validate' with code {} [GET].", code);

        return new ResponseEntity<>(discountCodeService.validateDiscountCode(code), HttpStatus.OK);
    }
}
