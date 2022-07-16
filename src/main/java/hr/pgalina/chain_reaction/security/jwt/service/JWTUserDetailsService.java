package hr.pgalina.chain_reaction.security.jwt.service;

import hr.pgalina.chain_reaction.domain.entity.User;
import hr.pgalina.chain_reaction.domain.exception.BadRequestException;
import hr.pgalina.chain_reaction.domain.repository.UserRepository;
import hr.pgalina.chain_reaction.security.jwt.constants.AuthoritiesConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

import static hr.pgalina.chain_reaction.domain.exception.contants.ErrorTypeConstants.ERROR;
import static hr.pgalina.chain_reaction.domain.exception.contants.ExceptionMessages.USER_DOES_NOT_EXIST;

@Slf4j
@Service
@RequiredArgsConstructor
public class JWTUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Entered loadUserByUsername in JWTUserDetailsService.");

        User user = userRepository
                .findUserByUsername(username)
                .orElseThrow(() -> new BadRequestException(ERROR, HttpStatus.NOT_FOUND, USER_DOES_NOT_EXIST));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), determineAuthority(user));
    }

    public Collection<? extends GrantedAuthority> determineAuthority(User user) {
        if (user.getIsAdmin()) {
            return new ArrayList<>() {{ add(new SimpleGrantedAuthority(AuthoritiesConstants.ADMIN)); }};
        } else {
            return new ArrayList<>() {{ add(new SimpleGrantedAuthority(AuthoritiesConstants.USER)); }};
        }
    }
}
