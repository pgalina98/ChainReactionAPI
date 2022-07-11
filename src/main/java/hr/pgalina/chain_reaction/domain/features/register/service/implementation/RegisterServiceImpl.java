package hr.pgalina.chain_reaction.domain.features.register.service.implementation;

import hr.pgalina.chain_reaction.domain.entity.User;
import hr.pgalina.chain_reaction.domain.exception.BadRequestException;
import hr.pgalina.chain_reaction.domain.exception.contants.ErrorTypeConstants;
import hr.pgalina.chain_reaction.domain.features.register.form.RegisterForm;
import hr.pgalina.chain_reaction.domain.features.register.service.RegisterService;
import hr.pgalina.chain_reaction.domain.mapper.UserMapper;
import hr.pgalina.chain_reaction.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static hr.pgalina.chain_reaction.domain.exception.contants.ExceptionMessages.USER_WITH_PROVIDED_USERNAME_ALREADY_EXISTS;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    @Override
    @Transactional
    public void createUser(RegisterForm registerForm) {
        log.info("Entered createUser in RegisterServiceImpl.");

        userRepository
            .findUserByUsername(registerForm.getUsername())
            .orElseThrow(() -> new BadRequestException(ErrorTypeConstants.ERROR, HttpStatus.BAD_REQUEST, USER_WITH_PROVIDED_USERNAME_ALREADY_EXISTS));

        User user = userMapper.mapFormToEntity(registerForm);

        userRepository.save(user);
    }
}
