package hr.pgalina.chain_reaction.domain.controller;

import hr.pgalina.chain_reaction.domain.features.login.form.LoginForm;
import hr.pgalina.chain_reaction.domain.features.login.service.LoginService;
import hr.pgalina.chain_reaction.security.jwt.dto.JWTTokenDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/authentication")
@RequiredArgsConstructor
public class AuthenticationController {

    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<JWTTokenDto> authenticateUser(@Valid @RequestBody LoginForm loginForm) {
        log.info("Entered '/api/authentication'.");

        return ResponseEntity.ok(loginService.authenticateUser(loginForm));
    }
}
