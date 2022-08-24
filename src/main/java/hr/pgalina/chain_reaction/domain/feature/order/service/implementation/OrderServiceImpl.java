package hr.pgalina.chain_reaction.domain.feature.order.service.implementation;

import hr.pgalina.chain_reaction.config.AsyncExecutor;
import hr.pgalina.chain_reaction.domain.entity.Order;
import hr.pgalina.chain_reaction.domain.feature.notification.service.NotificationService;
import hr.pgalina.chain_reaction.domain.feature.order.form.OrderForm;
import hr.pgalina.chain_reaction.domain.feature.order.service.OrderService;
import hr.pgalina.chain_reaction.domain.feature.order.validator.OrderValidator;
import hr.pgalina.chain_reaction.domain.feature.product.service.ProductService;
import hr.pgalina.chain_reaction.domain.mapper.OrderMapper;
import hr.pgalina.chain_reaction.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderValidator orderValidator;

    private final ProductService productService;
    private final NotificationService notificationService;

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    @Override
    @Transactional
    public synchronized void createOrder(OrderForm orderForm) {
        log.info("Entered createOrder in OrderServiceImpl.");

        orderValidator.validateOrderForm(orderForm);

        Order order = orderRepository.saveAndFlush(orderMapper.mapToEntity(orderForm));

        order
            .getProducts()
            .forEach(orderProduct -> productService.updateProductQuantity(orderProduct.getProduct().getIdProduct(), orderProduct.getProduct().getAvailableQuantity() - orderProduct.getQuantity()));

        AsyncExecutor.executeAfterTransactionCommits(() -> notificationService.createNotificationForSuccessfullyCreatedOrder(orderForm.getIdUser(), orderForm.getDeliveryType()));
    }
}
