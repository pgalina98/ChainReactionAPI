package hr.pgalina.chain_reaction.domain.feature.rent.validator;

import hr.pgalina.chain_reaction.domain.feature.rent.form.RentForm;

public interface RentValidator {

    void validateLocation(Short idLocation);

    void validateRentForm(RentForm rentForm);
}
