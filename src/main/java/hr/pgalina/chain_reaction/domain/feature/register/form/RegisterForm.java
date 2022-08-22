package hr.pgalina.chain_reaction.domain.feature.register.form;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@RequiredArgsConstructor
public class RegisterForm {

    @NotNull
    private String fullname;

    @NotNull
    private String username;

    @NotNull
    private String email;

    @NotNull
    private String password;
}
