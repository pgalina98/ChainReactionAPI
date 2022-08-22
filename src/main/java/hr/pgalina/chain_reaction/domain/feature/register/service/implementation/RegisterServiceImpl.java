package hr.pgalina.chain_reaction.domain.feature.register.service.implementation;

import hr.pgalina.chain_reaction.domain.entity.User;
import hr.pgalina.chain_reaction.domain.feature.register.form.RegisterForm;
import hr.pgalina.chain_reaction.domain.feature.register.service.RegisterService;
import hr.pgalina.chain_reaction.domain.feature.register.validator.RegisterValidator;
import hr.pgalina.chain_reaction.domain.mapper.UserMapper;
import hr.pgalina.chain_reaction.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final RegisterValidator registerValidator;

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    @Override
    @Transactional
    public void createUser(RegisterForm registerForm) {
        log.info("Entered createUser in RegisterServiceImpl.");

        registerValidator.validateUser(registerForm);

        User user = userMapper.mapToEntity(registerForm);

        userRepository.save(user);
    }
}
