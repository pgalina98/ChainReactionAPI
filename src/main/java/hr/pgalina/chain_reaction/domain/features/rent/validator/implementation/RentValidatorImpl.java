package hr.pgalina.chain_reaction.domain.features.rent.validator.implementation;

import hr.pgalina.chain_reaction.domain.entity.Rent;
import hr.pgalina.chain_reaction.domain.exception.BadRequestException;
import hr.pgalina.chain_reaction.domain.exception.contants.ErrorTypeConstants;
import hr.pgalina.chain_reaction.domain.features.order.validator.ProductValidator;
import hr.pgalina.chain_reaction.domain.features.register.validator.RegisterValidator;
import hr.pgalina.chain_reaction.domain.features.rent.enumeration.Location;
import hr.pgalina.chain_reaction.domain.features.rent.form.RentForm;
import hr.pgalina.chain_reaction.domain.features.rent.validator.RentValidator;
import hr.pgalina.chain_reaction.domain.repository.RentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

import static hr.pgalina.chain_reaction.domain.exception.contants.ExceptionMessages.LOCATION_DOES_NOT_EXIST;
import static hr.pgalina.chain_reaction.domain.exception.contants.ExceptionMessages.RENT_FOR_SOME_OF_TIMESLOTS_ALREADY_EXISTS;

@Slf4j
@Component
@RequiredArgsConstructor
public class RentValidatorImpl implements RentValidator {

    private final RegisterValidator registerValidator;
    private final ProductValidator productValidator;

    private final RentRepository rentRepository;

    @Override
    public void validateLocation(Short idLocation) {
        log.info("Entered validateLocation in RentEBikeValidatorImpl with idLocation {}.", idLocation);

        Location location = Location.findByIdLocation(idLocation);

        if(Objects.isNull(location)) {
            throw new BadRequestException(ErrorTypeConstants.ERROR, HttpStatus.NOT_FOUND, LOCATION_DOES_NOT_EXIST);
        }
    }

    @Override
    public void validateRentForm(RentForm rentForm) {
        log.info("Entered validateRentForm in RentEBikeValidatorImpl.");

        registerValidator.validateUser(rentForm.getIdUser());
        productValidator.validateProduct(rentForm.getProduct().getIdProduct());
        productValidator.validateProduct(rentForm.getHelmet().getIdProduct());
        validateLocation(rentForm.getLocation().getIdLocation());
        validateDateAndTimeslots(
            rentForm.getProduct().getIdProduct(),
            rentForm.getDate(),
            rentForm.getTimeslots()
        );
    }

    private void validateDateAndTimeslots(Long idProduct, LocalDate date, List<LocalTime> timeslots) {
        List<Rent> productRentals = rentRepository
                .findAllByIdProductAndDate(idProduct, date);

        boolean isAnyOfTimeslotsOverlapsWithExistingProductRentals =
            productRentals
                .stream()
                .anyMatch(
                    productRent ->
                        timeslots
                            .stream()
                            .anyMatch(
                                timeslot ->
                                    (timeslot.isAfter(productRent.getActiveFrom()) || timeslot.equals(productRent.getActiveFrom())) &&
                                    (timeslot.isBefore(productRent.getActiveTo()) || timeslot.equals(productRent.getActiveTo()))
                            )
                );

        if (isAnyOfTimeslotsOverlapsWithExistingProductRentals) {
            throw new BadRequestException(ErrorTypeConstants.ERROR, HttpStatus.BAD_REQUEST, RENT_FOR_SOME_OF_TIMESLOTS_ALREADY_EXISTS);
        }
    }
}
