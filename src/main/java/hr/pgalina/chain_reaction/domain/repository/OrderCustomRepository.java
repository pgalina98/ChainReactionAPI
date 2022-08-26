package hr.pgalina.chain_reaction.domain.repository;

import com.querydsl.core.BooleanBuilder;
import hr.pgalina.chain_reaction.domain.entity.Order;
import hr.pgalina.chain_reaction.domain.feature.order.dto.OrderPage;
import hr.pgalina.chain_reaction.domain.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import static hr.pgalina.chain_reaction.domain.entity.QOrder.order;

@Component
@RequiredArgsConstructor
public class OrderCustomRepository {

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    public OrderPage findAllByPageable(
        PageRequest pageable,
        Long idUser
    ) {
        BooleanBuilder where = new BooleanBuilder();

        where
            .and(order.user.idUser.eq(idUser));

        Page<Order> ordersPage = orderRepository.findAll(where, pageable);

        return OrderPage
            .builder()
            .totalElements(ordersPage.getTotalElements())
            .orders(orderMapper.mapToDtos(ordersPage.getContent()))
            .build();
    }
}
