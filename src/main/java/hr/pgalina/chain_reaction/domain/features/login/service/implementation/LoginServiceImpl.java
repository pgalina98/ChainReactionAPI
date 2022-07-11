package hr.pgalina.chain_reaction.domain.features.login.service.implementation;

import hr.pgalina.chain_reaction.domain.entity.User;
import hr.pgalina.chain_reaction.domain.exception.BadRequestException;
import hr.pgalina.chain_reaction.domain.exception.contants.ErrorTypeConstants;
import hr.pgalina.chain_reaction.domain.features.login.form.LoginForm;
import hr.pgalina.chain_reaction.domain.features.login.service.LoginService;
import hr.pgalina.chain_reaction.domain.repository.UserRepository;
import hr.pgalina.chain_reaction.security.jwt.TokenProvider;
import hr.pgalina.chain_reaction.security.jwt.dto.JWTTokenDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static hr.pgalina.chain_reaction.domain.exception.contants.ExceptionMessages.INVALID_USERNAME_OR_PASSWORD;
import static hr.pgalina.chain_reaction.domain.exception.contants.ExceptionMessages.USER_DOES_NOT_EXIST;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final TokenProvider tokenProvider;

    @Override
    @Transactional(readOnly = true)
    public JWTTokenDto authenticateUser(LoginForm loginForm) throws AuthenticationException {
        log.info("Entered authenticateUser in LoginServiceImpl.");

        User user = userRepository
            .findUserByUsername(loginForm.getUsername())
            .orElseThrow(() -> new BadRequestException(ErrorTypeConstants.ERROR, HttpStatus.NOT_FOUND, USER_DOES_NOT_EXIST));

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            loginForm.getUsername(),
            loginForm.getPassword()
        );

        Authentication authentication;
        
        try {
            authentication = authenticationManager.authenticate(authenticationToken);
        } catch (AuthenticationException ex) {
            throw new BadRequestException(ErrorTypeConstants.ERROR, HttpStatus.UNAUTHORIZED, INVALID_USERNAME_OR_PASSWORD);
        }
        
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String autheticationToken = tokenProvider.createToken(user, authentication);
        String refreshToken = tokenProvider.createRefreshToken(user, authentication);

        return new JWTTokenDto(autheticationToken, refreshToken);
    }
}
