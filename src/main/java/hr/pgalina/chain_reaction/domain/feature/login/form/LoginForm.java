package hr.pgalina.chain_reaction.domain.feature.login.form;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@RequiredArgsConstructor
public class LoginForm {

    @NotNull
    private String username;

    @NotNull
    private String password;
}
