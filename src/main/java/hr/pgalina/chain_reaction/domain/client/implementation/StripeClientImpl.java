package hr.pgalina.chain_reaction.domain.client.implementation;

import com.stripe.Stripe;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import hr.pgalina.chain_reaction.domain.client.StripeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class StripeClientImpl implements StripeClient {

    private static final String API_KEY = "sk_test_51LZIBFA6Uy0e7KrhOMMwR3agKOf6ZFAs5nqZdjvWKDFAHKp1nFPL8x1kevHZLvlQTK5l9o93ywZBtOFaum9MldoR00hfq2AKRT";

    @Autowired
    StripeClientImpl() {
        Stripe.apiKey = API_KEY;
    }

    @Override
    public Customer createCustomer(String token, String email) throws Exception {
        Map<String, Object> customerParams = new HashMap<String, Object>();

        customerParams.put("email", email);
        customerParams.put("source", token);

        return Customer.create(customerParams);
    }

    private Customer getCustomer(String idCustomer) throws Exception {
        return Customer.retrieve(idCustomer);
    }

    @Override
    public Charge charge(String token, Double amount) throws Exception {
        Map<String, Object> chargeParams = new HashMap<String, Object>();

        chargeParams.put("amount", (int)(amount * 100));
        chargeParams.put("currency", "USD");
        chargeParams.put("source", token);
        Charge charge = Charge.create(chargeParams);

        return charge;
    }

    @Override
    public Charge chargeCustomersCard(String customerId, Integer amount) throws Exception {
        String sourceCard = getCustomer(customerId).getDefaultSource();
        Map<String, Object> chargeParams = new HashMap<String, Object>();

        chargeParams.put("amount", amount);
        chargeParams.put("currency", "USD");
        chargeParams.put("customer", customerId);
        chargeParams.put("source", sourceCard);
        Charge charge = Charge.create(chargeParams);

        return charge;
    }
}