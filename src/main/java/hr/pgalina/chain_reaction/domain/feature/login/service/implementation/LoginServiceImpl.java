package hr.pgalina.chain_reaction.domain.feature.login.service.implementation;

import hr.pgalina.chain_reaction.domain.entity.User;
import hr.pgalina.chain_reaction.domain.exception.BadRequestException;
import hr.pgalina.chain_reaction.domain.exception.constant.ErrorTypeConstants;
import hr.pgalina.chain_reaction.domain.feature.login.form.LoginForm;
import hr.pgalina.chain_reaction.domain.feature.login.service.LoginService;
import hr.pgalina.chain_reaction.domain.repository.UserRepository;
import hr.pgalina.chain_reaction.security.jwt.TokenProvider;
import hr.pgalina.chain_reaction.security.jwt.dto.JWTTokenDto;
import hr.pgalina.chain_reaction.security.jwt.service.JWTUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static hr.pgalina.chain_reaction.domain.exception.constant.ExceptionMessages.INVALID_USERNAME_OR_PASSWORD;
import static hr.pgalina.chain_reaction.domain.exception.constant.ExceptionMessages.USER_DOES_NOT_EXIST;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final JWTUserDetailsService jwtUserDetailsService;

    private final TokenProvider tokenProvider;

    @Override
    @Transactional(readOnly = true)
    public JWTTokenDto authenticateUser(LoginForm loginForm) {
        log.info("Entered authenticateUser in LoginServiceImpl.");

        User user = userRepository
            .findUserByUsername(loginForm.getUsername())
            .orElseThrow(() -> new BadRequestException(ErrorTypeConstants.ERROR, HttpStatus.NOT_FOUND, USER_DOES_NOT_EXIST));

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            loginForm.getUsername(),
            loginForm.getPassword(),
            jwtUserDetailsService.determineAuthority(user)
        );

        boolean isPasswordMatched = passwordEncoder.matches(loginForm.getPassword(), user.getPassword());

        if(!isPasswordMatched) {
            throw new BadRequestException(ErrorTypeConstants.ERROR, HttpStatus.UNAUTHORIZED, INVALID_USERNAME_OR_PASSWORD);
        }
        
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        String token = tokenProvider.createToken(user, authenticationToken);
        String refreshToken = tokenProvider.createRefreshToken(user, authenticationToken);

        return new JWTTokenDto(token, refreshToken);
    }
}
