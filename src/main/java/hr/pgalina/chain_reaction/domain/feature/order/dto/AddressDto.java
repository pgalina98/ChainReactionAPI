package hr.pgalina.chain_reaction.domain.feature.order.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class AddressDto implements Serializable {

    private String city;

    private String address;

    private String zipCode;
}
