package hr.pgalina.chain_reaction.domain.mapper;

import hr.pgalina.chain_reaction.domain.entity.Order;
import hr.pgalina.chain_reaction.domain.entity.OrderProduct;
import hr.pgalina.chain_reaction.domain.entity.Product;
import hr.pgalina.chain_reaction.domain.exception.BadRequestException;
import hr.pgalina.chain_reaction.domain.exception.constant.ErrorTypeConstants;
import hr.pgalina.chain_reaction.domain.feature.cart.dto.CartItemDto;
import hr.pgalina.chain_reaction.domain.feature.order.dto.OrderProductDto;
import hr.pgalina.chain_reaction.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static hr.pgalina.chain_reaction.domain.exception.constant.ExceptionMessages.PRODUCT_DOES_NOT_EXIST;

@Component
@RequiredArgsConstructor
public class OrderProductMapper {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    public OrderProduct mapToEntity(CartItemDto cartItemDto, Order order) {
        OrderProduct orderProduct = new OrderProduct();

    Product product = productRepository
        .findById(cartItemDto.getIdProduct())
        .orElseThrow(() -> new BadRequestException(ErrorTypeConstants.ERROR, HttpStatus.NOT_FOUND, PRODUCT_DOES_NOT_EXIST));

        orderProduct.setProduct(product);
        orderProduct.setOrder(order);
        orderProduct.setQuantity(cartItemDto.getQuantity());

        return orderProduct;
    }

    public List<OrderProduct> mapToEntities(List<CartItemDto> cartItems, Order order) {
        return cartItems
            .stream()
            .map((cartItemDto) -> mapToEntity(cartItemDto, order))
            .collect(Collectors.toList());
    }

    public OrderProductDto mapToDto(OrderProduct orderProduct) {
        OrderProductDto orderProductDto = new OrderProductDto();

        orderProductDto.setProduct(productMapper.mapToDto(orderProduct.getProduct()));
        orderProductDto.setQuantity(orderProduct.getQuantity());

        return orderProductDto;
    }

    public List<OrderProductDto> mapToDtos(List<OrderProduct> orderProducts) {
        return orderProducts
            .stream()
            .map((orderProduct) -> mapToDto(orderProduct))
            .collect(Collectors.toList());
    }
}
