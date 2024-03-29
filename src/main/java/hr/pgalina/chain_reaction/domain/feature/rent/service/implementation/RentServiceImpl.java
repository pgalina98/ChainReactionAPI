package hr.pgalina.chain_reaction.domain.feature.rent.service.implementation;

import hr.pgalina.chain_reaction.config.AsyncExecutor;
import hr.pgalina.chain_reaction.domain.entity.Rent;
import hr.pgalina.chain_reaction.domain.feature.notification.service.NotificationService;
import hr.pgalina.chain_reaction.domain.feature.rent.dto.LocationDto;
import hr.pgalina.chain_reaction.domain.feature.rent.enumeration.Location;
import hr.pgalina.chain_reaction.domain.feature.rent.enumeration.Workday;
import hr.pgalina.chain_reaction.domain.feature.rent.form.RentForm;
import hr.pgalina.chain_reaction.domain.feature.rent.service.RentService;
import hr.pgalina.chain_reaction.domain.feature.rent.validator.RentValidator;
import hr.pgalina.chain_reaction.domain.mapper.LocationMapper;
import hr.pgalina.chain_reaction.domain.mapper.RentMapper;
import hr.pgalina.chain_reaction.domain.repository.RentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RentServiceImpl implements RentService {

    private final RentValidator rentValidator;

    private final RentRepository rentRepository;

    private final NotificationService notificationService;

    private final RentMapper rentMapper;
    private final LocationMapper locationMapper;

    @Override
    @Transactional(readOnly = true)
    public List<LocalDateTime> getAvailableTimeslots(Long idProduct, Short idLocation, LocalDate date) {
        log.info("Entered getAvailableTimeslots in RentEBikeServiceImpl with idProduct {}, idLocation {} and date {}.", idProduct, idLocation, date);

        rentValidator.validateLocation(idLocation);

        List<LocalDateTime> availableTimeslots = new ArrayList<>();

        Workday currentWorkday =  Workday.findByDate(date);

        List<Rent> productRentals = rentRepository
            .findRentsByIdProductAndDate(idProduct, date);

        for (LocalTime hour = currentWorkday.getStartTime(); hour.isBefore(currentWorkday.getEndTime()); hour = hour.plusHours(1)  )  {
            final LocalTime finalHour = hour;

            boolean isCurrentHourBetweenAnyOfOccupiedTimeslots = productRentals
                .stream()
                .anyMatch(
                    productRent ->
                        (finalHour.isAfter(productRent.getActiveFrom()) || finalHour.equals(productRent.getActiveFrom())) &&
                        (finalHour.isBefore(productRent.getActiveTo()))
                );

            boolean isCurrentDate = date.isEqual(LocalDate.now());
            boolean isAfterCurrentTimeWithOffset = finalHour.isAfter(LocalTime.now().plusMinutes(30));

            if (isCurrentDate) {
                if (isAfterCurrentTimeWithOffset && !isCurrentHourBetweenAnyOfOccupiedTimeslots) {
                    availableTimeslots.add(LocalDateTime.of(date, hour));
                }
            } else {
                if (!isCurrentHourBetweenAnyOfOccupiedTimeslots) {
                    availableTimeslots.add(LocalDateTime.of(date, hour));
                }
            }
        }

        return availableTimeslots;
    }

    @Override
    @Transactional
    public synchronized void createRent(RentForm rentForm) {
        log.info("Entered saveRent in RentEBikeServiceImpl with idProduct {}, idLocation {} and date {}.",
            rentForm.getProduct().getIdProduct(),
            rentForm.getLocation().getIdLocation(),
            rentForm.getDate()
        );

        rentValidator.validateRentForm(rentForm);

        List<Rent> productRentals = rentMapper.mapToEntity(rentForm);

        rentRepository.saveAll(productRentals);

        AsyncExecutor.executeAfterTransactionCommits(() -> notificationService.createNotificationForSuccessfullyCreatedRent(rentForm.getIdUser(), productRentals));
    }

    @Override
    @Transactional(readOnly = true)
    public List<LocationDto> getAvailableRentLocations() {
        log.info("Entered getAvailableRentLocations in RentEBikeServiceImpl.");

        return locationMapper.mapToDtos(Location.ALL_LOCATIONS);
    }
}
