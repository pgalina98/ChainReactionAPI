package hr.pgalina.chain_reaction.domain.features.login.service.implementation;

import hr.pgalina.chain_reaction.domain.entity.User;
import hr.pgalina.chain_reaction.domain.features.login.form.LoginForm;
import hr.pgalina.chain_reaction.domain.features.login.service.LoginService;
import hr.pgalina.chain_reaction.domain.repository.UserRepository;
import hr.pgalina.chain_reaction.security.jwt.TokenProvider;
import hr.pgalina.chain_reaction.security.jwt.dto.JWTTokenDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final TokenProvider tokenProvider;

    @Override
    @Transactional(readOnly = true)
    public JWTTokenDto authenticateUser(LoginForm loginForm) {
        log.info("Entered authenticateUser in LoginServiceImpl.");

        User user = userRepository
            .findUserByUsername(loginForm.getUsername())
            .orElse(null);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            loginForm.getUsername(),
            loginForm.getPassword()
        );

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(user, authentication);
        String refreshToken = tokenProvider.createRefreshToken(user, authentication);

        return new JWTTokenDto(token, refreshToken);
    }
}
