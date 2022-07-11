package hr.pgalina.chain_reaction.domain.features.login.service;

import hr.pgalina.chain_reaction.domain.features.login.form.LoginForm;
import hr.pgalina.chain_reaction.security.jwt.dto.JWTTokenDto;

public interface LoginService {

    JWTTokenDto authenticateUser(LoginForm loginForm);
}
