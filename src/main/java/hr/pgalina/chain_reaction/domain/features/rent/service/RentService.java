package hr.pgalina.chain_reaction.domain.features.rent.service;

import hr.pgalina.chain_reaction.domain.features.rent.dto.LocationDto;
import hr.pgalina.chain_reaction.domain.features.rent.form.RentForm;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface RentService {

    List<LocalDateTime> getAvailableTimeslots(Long idProduct, Short idLocation, LocalDate date);

    List<LocationDto> getAvailableRentLocations();

    void createRent(RentForm rentForm);
}
