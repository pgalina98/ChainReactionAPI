package hr.pgalina.chain_reaction.domain.features.rent.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface RentService {

    List<LocalTime> getAvailableTimeslots(Long idProduct, Short idLocation, LocalDate date);
}
