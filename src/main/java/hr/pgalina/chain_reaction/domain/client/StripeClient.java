package hr.pgalina.chain_reaction.domain.client;

import com.stripe.model.Charge;
import com.stripe.model.Customer;

public interface StripeClient {

    Customer createCustomer(String token, String email) throws Exception;

    Charge charge(String token, Double amount) throws Exception;

    Charge chargeCustomersCard(String customerId, Integer amount) throws Exception;
}
