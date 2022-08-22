package hr.pgalina.chain_reaction.domain.mapper;

import hr.pgalina.chain_reaction.domain.entity.User;
import hr.pgalina.chain_reaction.domain.feature.register.form.RegisterForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public User mapToEntity(RegisterForm registerForm) {
        User user = new User();

        user.setFullname(registerForm.getFullname());
        user.setUsername(registerForm.getUsername());
        user.setEmail(registerForm.getEmail());
        user.setPassword(passwordEncoder.encode(registerForm.getPassword()));

        return user;
    }
}
