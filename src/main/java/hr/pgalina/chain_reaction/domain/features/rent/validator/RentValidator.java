package hr.pgalina.chain_reaction.domain.features.rent.validator;

import hr.pgalina.chain_reaction.domain.features.rent.form.RentForm;

public interface RentValidator {

    void validateLocation(Short idLocation);

    void validateRentForm(RentForm rentForm);
}
