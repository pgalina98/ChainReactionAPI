package hr.pgalina.chain_reaction.domain.feature.order.validator;

import hr.pgalina.chain_reaction.domain.feature.order.form.OrderForm;

public interface OrderValidator {

    void validateOrderForm(OrderForm orderForm);
}
