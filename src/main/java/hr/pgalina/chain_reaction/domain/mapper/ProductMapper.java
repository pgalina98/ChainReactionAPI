package hr.pgalina.chain_reaction.domain.mapper;

import hr.pgalina.chain_reaction.domain.entity.Product;
import hr.pgalina.chain_reaction.domain.feature.product.dto.ProductDto;
import hr.pgalina.chain_reaction.domain.feature.product.enumeration.ProductColor;
import hr.pgalina.chain_reaction.domain.feature.product.enumeration.ProductType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final ProductColorMapper productColorMapper;
    private final ProductTypeMapper productTypeMapper;

    public ProductDto mapToDto(Product product) {
        ProductDto productDto = new ProductDto();

        productDto.setIdProduct(product.getIdProduct());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setModel(product.getModel());
        productDto.setAssistSpeed(product.getAssistSpeed());
        productDto.setBatteryRange(product.getBatteryRange());
        productDto.setChargingTime(product.getChargingTime());
        productDto.setWeight(product.getWeight());
        productDto.setPrice(product.getPrice());
        productDto.setColor(
            productColorMapper.mapToDto(ProductColor.findByIdProductColor(product.getColor()))
        );
        productDto.setAvailableQuantity(product.getAvailableQuantity());
        productDto.setForRent(product.getForRent());
        productDto.setRentPricePerHour(product.getRentPricePerHour());
        productDto.setImagePath(product.getImagePath());
        productDto.setType(
            productTypeMapper.mapToDto(ProductType.findByIdProductType(product.getType()))
        );

        return productDto;
    }

    public List<ProductDto> mapToDtos(List<Product> products) {
        return products
            .stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }
}
