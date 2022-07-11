package hr.pgalina.chain_reaction.domain.features.register.service;

import hr.pgalina.chain_reaction.domain.features.register.form.RegisterForm;

public interface RegisterService {

    void createUser(RegisterForm registerForm);
}
