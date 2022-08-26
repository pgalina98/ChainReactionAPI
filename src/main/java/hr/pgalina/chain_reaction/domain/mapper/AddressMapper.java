package hr.pgalina.chain_reaction.domain.mapper;

import hr.pgalina.chain_reaction.domain.feature.order.dto.AddressDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressMapper {

    public AddressDto mapToDto(String city, String address, String zipCode) {
        AddressDto addressDto = new AddressDto();

        addressDto.setCity(city);
        addressDto.setAddress(address);
        addressDto.setZipCode(zipCode);

        return addressDto;
    }
}
