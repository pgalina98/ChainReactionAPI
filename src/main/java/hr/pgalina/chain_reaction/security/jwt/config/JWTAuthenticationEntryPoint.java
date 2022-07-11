package hr.pgalina.chain_reaction.security.jwt.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import hr.pgalina.chain_reaction.domain.exception.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static hr.pgalina.chain_reaction.domain.exception.contants.ErrorTypeConstants.ERROR;
import static hr.pgalina.chain_reaction.domain.exception.contants.ExceptionMessages.INVALID_USERNAME_OR_PASSWORD;

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
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException ex) throws IOException {
        ServerHttpResponse outputMessage = new ServletServerHttpResponse(httpServletResponse);
        outputMessage.setStatusCode(HttpStatus.UNAUTHORIZED);

        BadRequestException badRequestException = new BadRequestException(ERROR, HttpStatus.UNAUTHORIZED, INVALID_USERNAME_OR_PASSWORD);

        messageConverter.write(mapper.writeValueAsString(badRequestException), MediaType.APPLICATION_JSON, outputMessage);
    }
}
