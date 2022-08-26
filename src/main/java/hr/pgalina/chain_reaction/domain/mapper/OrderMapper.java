package hr.pgalina.chain_reaction.domain.mapper;

import hr.pgalina.chain_reaction.domain.constant.DateTimeConstants;
import hr.pgalina.chain_reaction.domain.entity.DiscountCode;
import hr.pgalina.chain_reaction.domain.entity.Order;
import hr.pgalina.chain_reaction.domain.entity.User;
import hr.pgalina.chain_reaction.domain.exception.BadRequestException;
import hr.pgalina.chain_reaction.domain.exception.constant.ErrorTypeConstants;
import hr.pgalina.chain_reaction.domain.feature.order.dto.OrderDto;
import hr.pgalina.chain_reaction.domain.feature.order.enumeration.DeliveryType;
import hr.pgalina.chain_reaction.domain.feature.order.form.OrderForm;
import hr.pgalina.chain_reaction.domain.repository.DiscountCodeRepository;
import hr.pgalina.chain_reaction.domain.repository.UserRepository;
import hr.pgalina.chain_reaction.domain.util.DateTimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static hr.pgalina.chain_reaction.domain.exception.constant.ExceptionMessages.DISCOUNT_CODE_DOES_NOT_EXIST;
import static hr.pgalina.chain_reaction.domain.exception.constant.ExceptionMessages.USER_DOES_NOT_EXIST;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final String ESTIMATED_DELIVERY_FORMAT = "%s - %s";

    private final UserRepository userRepository;
    private final DiscountCodeRepository discountCodeRepository;

    private final OrderProductMapper orderProductMapper;
    private final DeliveryTypeMapper deliveryTypeMapper;
    private final AddressMapper addressMapper;

    public Order mapToEntity(OrderForm orderForm) {
        Order order = new Order();

        User user = userRepository
            .findById(orderForm.getIdUser())
            .orElseThrow(() -> new BadRequestException(ErrorTypeConstants.ERROR, HttpStatus.NOT_FOUND, USER_DOES_NOT_EXIST));

        order.setUser(user);
        order.setBuyer(orderForm.getBuyer());
        order.setPhoneNumber(orderForm.getPhoneNumber());
        order.setDeliveryType(orderForm.getDeliveryType().getIdDeliveryType());
        order.setCity(orderForm.getDeliveryAddress().getCity());
        order.setAddress(orderForm.getDeliveryAddress().getAddress());
        order.setZipCode(orderForm.getDeliveryAddress().getZipCode());
        order.setPaymentMethod(orderForm.getPaymentMethod().getIdPaymentMethod());
        order.setCardNumber(orderForm.getCreditCardDetails().getCardNumber());
        order.setExpirationDate(orderForm.getCreditCardDetails().getExpirationDate());
        order.setCardholder(orderForm.getCreditCardDetails().getCardholder());
        order.setCvv(orderForm.getCreditCardDetails().getCvv());

        if (orderForm.getUseDiscountCode()) {
            DiscountCode discountCode = discountCodeRepository
                .findById(orderForm.getDiscountCode().getIdDiscountCode())
                .orElseThrow(() -> new BadRequestException(ErrorTypeConstants.ERROR, HttpStatus.NOT_FOUND, DISCOUNT_CODE_DOES_NOT_EXIST));

            order.setDiscountCode(discountCode);
        }

        order.setProducts(orderProductMapper.mapToEntities(orderForm.getProducts(), order));
        order.setTotal(orderForm.getTotal());

        return order;
    }

    public OrderDto mapToDto(Order order) {
        OrderDto orderDto = new OrderDto();

        orderDto.setIdOrder(order.getIdOrder());
        orderDto.setProducts(orderProductMapper.mapToDtos(order.getProducts()));
        orderDto.setDeliveryType(deliveryTypeMapper.mapToDto(DeliveryType.findByIdDeliveryType(order.getDeliveryType())));
        orderDto.setEstimatedDelivery(
            String.format(
                ESTIMATED_DELIVERY_FORMAT,
                DateTimeUtils.formatDateWithLocale(order.getCreatedTimestamp().toLocalDate().plusDays(DeliveryType.fromIdDeliveryType(order.getDeliveryType()).getMinimumArrivalDays()), DateTimeConstants.APP_LOCAL_DATE_WITH_DAY_AND_MONTH_NAME_FORMAT, Locale.ENGLISH),
                DateTimeUtils.formatDateWithLocale(order.getCreatedTimestamp().toLocalDate().plusDays(DeliveryType.fromIdDeliveryType(order.getDeliveryType()).getMaximumArrivalDays()), DateTimeConstants.APP_LOCAL_DATE_WITH_DAY_AND_MONTH_NAME_FORMAT, Locale.ENGLISH)
            )
        );
        orderDto.setAddress(addressMapper.mapToDto(order.getCity(), order.getAddress(), order.getZipCode()));
        orderDto.setTotal(order.getTotal());

        return orderDto;
    }

    public List<OrderDto> mapToDtos(List<Order> orders) {
        return orders
            .stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }
}
