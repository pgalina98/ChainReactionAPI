package hr.pgalina.chain_reaction.domain.features.rent.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface RentService {

    List<LocalDateTime> getAvailableTimeslots(Long idProduct, Short idLocation, LocalDate date);
}
