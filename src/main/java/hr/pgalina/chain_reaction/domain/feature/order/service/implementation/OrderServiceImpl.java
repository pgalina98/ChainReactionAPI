package hr.pgalina.chain_reaction.domain.feature.order.service.implementation;

import hr.pgalina.chain_reaction.domain.feature.order.form.OrderForm;
import hr.pgalina.chain_reaction.domain.feature.order.service.OrderService;
import hr.pgalina.chain_reaction.domain.feature.order.validator.OrderValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderValidator orderValidator;

    @Override
    @Transactional
    public synchronized void createOrder(OrderForm orderForm) {
        log.info("Entered createOrder in OrderServiceImpl.");

        orderValidator.validateOrderForm(orderForm);
    }
}
