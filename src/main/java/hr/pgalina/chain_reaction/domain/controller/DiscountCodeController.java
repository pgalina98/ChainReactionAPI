package hr.pgalina.chain_reaction.domain.controller;

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

    @PostMapping("/{code}/validate")
    public ResponseEntity<?> validateDiscountCode(@PathVariable String code) {
        log.info("Entered '/api/discount-codes/{discountCode}/validate' with code {} [GET].", code);



        return new ResponseEntity<>(HttpStatus.OK);
    }
}
