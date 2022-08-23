package hr.pgalina.chain_reaction.domain.feature.order.service;

import hr.pgalina.chain_reaction.domain.feature.order.form.OrderForm;

public interface OrderService {

    void createOrder(OrderForm orderForm);
}
