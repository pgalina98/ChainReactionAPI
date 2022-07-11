package hr.pgalina.chain_reaction.domain.controller;

import hr.pgalina.chain_reaction.domain.features.register.form.RegisterForm;
import hr.pgalina.chain_reaction.domain.features.register.service.RegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final RegisterService registerService;

    @PostMapping
    public ResponseEntity createUser(@Valid @RequestBody RegisterForm registerForm) {
        log.info("Entered '/api/users' [POST].");

        registerService.createUser(registerForm);

        return new ResponseEntity(HttpStatus.CREATED);
    }
}
