package hr.pgalina.chain_reaction.domain.feature.register.service;

import hr.pgalina.chain_reaction.domain.feature.register.form.RegisterForm;

public interface RegisterService {

    void createUser(RegisterForm registerForm);
}
