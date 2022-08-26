package hr.pgalina.chain_reaction.domain.feature.order.service;

import hr.pgalina.chain_reaction.domain.feature.order.dto.OrderPage;
import hr.pgalina.chain_reaction.domain.feature.order.form.OrderForm;

public interface OrderService {

    OrderPage getAllOrdersForUser(Integer page, Integer size, Long idUser);

    void createOrder(OrderForm orderForm);
}
