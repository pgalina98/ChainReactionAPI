package hr.pgalina.chain_reaction.domain.features.rent.service.implementation;

import hr.pgalina.chain_reaction.domain.entity.Rent;
import hr.pgalina.chain_reaction.domain.features.rent.enumeration.Workday;
import hr.pgalina.chain_reaction.domain.features.rent.service.RentService;
import hr.pgalina.chain_reaction.domain.repository.RentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RentServiceImpl implements RentService {

    private final RentRepository rentRepository;

    @Override
    public List<LocalDateTime> getAvailableTimeslots(Long idProduct, Short idLocation, LocalDate date) {
        log.info("Entered getAvailableTimeslots in RentEBikeServiceImpl with idProduct {}, idLocation {} and date {}.", idProduct, idLocation, date);

        List<LocalDateTime> availableTimeslots = new ArrayList<>();

        Workday currentWorkday =  Workday.findByCurrentDay();

        List<Rent> productRentals = rentRepository
            .findAllByDate(date);

        for (LocalTime hour = currentWorkday.getStartTime(); hour.isBefore(currentWorkday.getEndTime()); hour = hour.plusHours(1)  )  {
            final LocalTime finalHour = hour;

            boolean isCurrentHourBetweenAnyOfOccupiedTimeslots = productRentals
                .stream()
                .anyMatch(
                    productRent ->
                        (finalHour.isAfter(productRent.getActiveFrom()) || finalHour.equals(productRent.getActiveFrom())) &&
                        (finalHour.isBefore(productRent.getActiveTo()) || finalHour.equals(productRent.getActiveTo()))
                );

            if (!isCurrentHourBetweenAnyOfOccupiedTimeslots) {
                availableTimeslots.add(LocalDateTime.of(date, hour));
            }
        }

        return availableTimeslots;
    }
}
