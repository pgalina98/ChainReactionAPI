package hr.pgalina.chain_reaction.domain.controller;

import com.stripe.model.Charge;
import hr.pgalina.chain_reaction.domain.client.StripeClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final StripeClient stripeClient;

    @GetMapping("/charge")
    public Charge chargeCard(@RequestHeader(value = "token") String token, @RequestParam Double amount) throws Exception {
        log.info("Entered '/api/payments/charge' with token {} and amount {} [POST].", token, amount);

        return stripeClient.charge(token, amount);
    }
}
