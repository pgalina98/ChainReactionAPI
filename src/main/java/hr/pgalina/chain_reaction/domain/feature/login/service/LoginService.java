package hr.pgalina.chain_reaction.domain.feature.login.service;

import hr.pgalina.chain_reaction.domain.feature.login.form.LoginForm;
import hr.pgalina.chain_reaction.security.jwt.dto.JWTTokenDto;

public interface LoginService {

    JWTTokenDto authenticateUser(LoginForm loginForm);
}
