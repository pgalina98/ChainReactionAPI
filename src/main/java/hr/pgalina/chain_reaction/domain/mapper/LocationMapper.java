package hr.pgalina.chain_reaction.domain.mapper;

import hr.pgalina.chain_reaction.domain.features.rent.dto.LocationDto;
import hr.pgalina.chain_reaction.domain.features.rent.enumeration.Location;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LocationMapper {

    public LocationDto mapToDto(Location location) {
        LocationDto locationDto = new LocationDto();

        locationDto.setIdLocation(location.getIdLocation());
        locationDto.setValue(location.getValue());

        return locationDto;
    }

    public List<LocationDto> mapToDtos(List<Location> locations) {
        return locations
            .stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }
}
