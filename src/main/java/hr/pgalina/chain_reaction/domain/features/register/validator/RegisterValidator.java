package hr.pgalina.chain_reaction.domain.features.register.validator;

import hr.pgalina.chain_reaction.domain.features.register.form.RegisterForm;

public interface RegisterValidator {

    void validateUser(RegisterForm registerForm);

    void validateUser(Long idUser);
}
