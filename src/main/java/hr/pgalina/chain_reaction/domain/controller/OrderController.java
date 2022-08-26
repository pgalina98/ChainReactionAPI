package hr.pgalina.chain_reaction.domain.controller;

import hr.pgalina.chain_reaction.domain.feature.order.dto.OrderPage;
import hr.pgalina.chain_reaction.domain.feature.order.form.OrderForm;
import hr.pgalina.chain_reaction.domain.feature.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<OrderPage> fetchAllOrdersForUser(
        @RequestParam(required = false, defaultValue = "1") Integer page,
        @RequestParam(required = false, defaultValue = "9999") Integer size,
        @RequestParam Long idUser
        ) {
        log.info("Entered '/api/orders' with user ID {} [GET].", idUser);

        return new ResponseEntity<>(orderService.getAllOrdersForUser(page, size, idUser), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> saveOrder(@RequestBody OrderForm orderForm) {
        log.info("Entered '/api/orders' [POST].");

        orderService.createOrder(orderForm);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
