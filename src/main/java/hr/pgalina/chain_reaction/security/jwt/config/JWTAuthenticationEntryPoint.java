package hr.pgalina.chain_reaction.security.jwt.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import hr.pgalina.chain_reaction.domain.exception.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static hr.pgalina.chain_reaction.domain.exception.contants.ErrorTypeConstants.ERROR;
import static hr.pgalina.chain_reaction.domain.exception.contants.ExceptionMessages.UNAUTHORIZED_REQUEST;

@Component
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final HttpMessageConverter<String> messageConverter;

    private final ObjectMapper mapper;

    public JWTAuthenticationEntryPoint() {
        this.messageConverter = new StringHttpMessageConverter();
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
    }

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException ex) throws BadRequestException {
        throw new BadRequestException(ERROR, HttpStatus.UNAUTHORIZED, UNAUTHORIZED_REQUEST);
    }
}
