package hr.pgalina.chain_reaction.domain.controller;

import com.stripe.model.Charge;
import hr.pgalina.chain_reaction.domain.client.StripeClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final StripeClient stripeClient;

    @GetMapping("/charge")
    public Charge chargeCard(@RequestHeader(value="token") String token, @RequestHeader(value="amount") Double amount) throws Exception {
        log.info("Entered '/api/payments/charge' with token {} [POST].", token);

        return stripeClient.charge(token, amount);
    }
}
