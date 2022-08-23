package hr.pgalina.chain_reaction.domain.controller;

import hr.pgalina.chain_reaction.domain.feature.order.form.OrderForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    @PostMapping
    public ResponseEntity<?> saveOrder(OrderForm orderForm) {
        log.info("Entered '/api/orders' [POST].");



        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
