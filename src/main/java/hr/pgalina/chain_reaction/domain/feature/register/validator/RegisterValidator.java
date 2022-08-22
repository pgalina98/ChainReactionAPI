package hr.pgalina.chain_reaction.domain.feature.register.validator;

import hr.pgalina.chain_reaction.domain.feature.register.form.RegisterForm;

public interface RegisterValidator {

    void validateUser(RegisterForm registerForm);

    void validateUser(Long idUser);
}
