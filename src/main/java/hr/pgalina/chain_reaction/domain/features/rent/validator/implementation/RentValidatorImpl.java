package hr.pgalina.chain_reaction.domain.features.rent.validator.implementation;

import hr.pgalina.chain_reaction.domain.exception.BadRequestException;
import hr.pgalina.chain_reaction.domain.exception.contants.ErrorTypeConstants;
import hr.pgalina.chain_reaction.domain.features.rent.enumeration.Location;
import hr.pgalina.chain_reaction.domain.features.rent.validator.RentValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static hr.pgalina.chain_reaction.domain.exception.contants.ExceptionMessages.LOCATION_DOES_NOT_EXIST;

@Slf4j
@Component
public class RentValidatorImpl implements RentValidator {

    @Override
    public void validateLocation(Short idLocation) {
        log.info("Entered validateLocation in RentEBikeValidatorImpl with idLocation {}.", idLocation);

        Location location = Location.findByIdLocation(idLocation);

        if(Objects.isNull(location)) {
            throw new BadRequestException(ErrorTypeConstants.ERROR, HttpStatus.NOT_FOUND, LOCATION_DOES_NOT_EXIST);
        }
    }
}
