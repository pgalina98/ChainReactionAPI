package hr.pgalina.chain_reaction.domain.features.register.validator.implementation;

import hr.pgalina.chain_reaction.domain.exception.BadRequestException;
import hr.pgalina.chain_reaction.domain.exception.constant.ErrorTypeConstants;
import hr.pgalina.chain_reaction.domain.features.register.form.RegisterForm;
import hr.pgalina.chain_reaction.domain.features.register.validator.RegisterValidator;
import hr.pgalina.chain_reaction.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import static hr.pgalina.chain_reaction.domain.exception.constant.ExceptionMessages.USER_WITH_PROVIDED_USERNAME_ALREADY_EXISTS;
import static hr.pgalina.chain_reaction.domain.exception.constant.ExceptionMessages.USER_DOES_NOT_EXIST;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegisterValidatorImpl implements RegisterValidator {

    private final UserRepository userRepository;

    @Override
    public void validateUser(RegisterForm registerForm) {
        log.info("Entered validateUser in RegisterValidatorImpl.");

        boolean usernameAlreadyRegistered = userRepository
            .existsByUsername(registerForm.getUsername())
            .get();

        if(usernameAlreadyRegistered) {
            throw new BadRequestException(ErrorTypeConstants.ERROR, HttpStatus.BAD_REQUEST, USER_WITH_PROVIDED_USERNAME_ALREADY_EXISTS);
        }
    }

    @Override
    public void validateUser(Long idUser) {
        log.info("Entered validateUser in RegisterValidatorImpl with idUser {}.", idUser);

        userRepository
            .existsByIdUser(idUser)
            .orElseThrow(() -> new BadRequestException(ErrorTypeConstants.ERROR, HttpStatus.BAD_REQUEST, USER_DOES_NOT_EXIST));
    }
}
