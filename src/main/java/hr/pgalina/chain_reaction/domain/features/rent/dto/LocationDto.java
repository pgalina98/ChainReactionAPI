package hr.pgalina.chain_reaction.domain.features.rent.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class LocationDto implements Serializable {

    private Short idLocation;

    private String value;
}
