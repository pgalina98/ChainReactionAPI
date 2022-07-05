package hr.pgalina.chain_reaction.security.jwt;

import hr.pgalina.chain_reaction.domain.entity.User;
import hr.pgalina.chain_reaction.domain.exception.BadRequestException;
import hr.pgalina.chain_reaction.security.utils.SecurityUtils;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import static hr.pgalina.chain_reaction.domain.exception.contants.ExceptionMessages.INVALID_JWT_TOKEN;
import static hr.pgalina.chain_reaction.domain.exception.contants.ExceptionMessages.UNKNOWN_JWT_TOKEN;
import static hr.pgalina.chain_reaction.security.jwt.constants.AuthoritiesConstants.ADMIN;
import static hr.pgalina.chain_reaction.security.jwt.constants.JWTConstants.*;

@Slf4j
@Component
public class TokenProvider {
    private Key key;

    private Long tokenValidityInMilliseconds;
    private Long refreshTokenValidityInMilliseconds;

    private JwtParser jwtParser;

    @Value("${jwt.token-validity-seconds}")
    private Long tokenValiditySeconds;

    @Value("${jwt.refresh-token-validity-seconds}")
    private Long refreshTokenValiditySeconds;

    @Value("${jwt.base64-secret}")
    private String base64Secret;

    @PostConstruct
    public void init() {
        byte[] keyBytes;
        keyBytes = Decoders.BASE64.decode(base64Secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.tokenValidityInMilliseconds = 1000 * tokenValiditySeconds;
        this.refreshTokenValidityInMilliseconds = 1000 * refreshTokenValiditySeconds;
        this.jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
    }

    public String createToken(User user, Authentication authentication) {
        log.info("Entered createToken in TokenProvider with authentication: {}.", authentication);

        String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        Date validity = new Date(now + this.tokenValidityInMilliseconds);

        return Jwts
            .builder()
            .claim(JWT_CLAIM_ID_USER, user.getIdUser())
            .claim(JWT_CLAIM_FULL_NAME, user.getFullName())
            .claim(JWT_CLAIM_USERNAME, user.getUsername())
            .claim(JWT_CLAIM_EMAIL_ADDRESS, user.getEmail())
            .claim(AUTHORITIES_KEY, authorities)
            .signWith(key, SignatureAlgorithm.HS512)
            .setExpiration(validity)
            .compact();
    }

    public String createToken(String refreshToken) {
        log.info("Entered createToken in TokenProvider with refreshToken: {}.", refreshToken);

        long now = (new Date()).getTime();
        Date validity = new Date(now + this.tokenValidityInMilliseconds);

        return Jwts
            .builder()
            .claim(JWT_CLAIM_ID_USER, getClaims(refreshToken).get(JWT_CLAIM_ID_USER))
            .claim(JWT_CLAIM_FULL_NAME, getClaims(refreshToken).get(JWT_CLAIM_FULL_NAME))
            .claim(JWT_CLAIM_USERNAME, getClaims(refreshToken).get(JWT_CLAIM_USERNAME))
            .claim(JWT_CLAIM_EMAIL_ADDRESS, getClaims(refreshToken).get(JWT_CLAIM_EMAIL_ADDRESS))
            .claim(AUTHORITIES_KEY, getClaims(refreshToken).get(AUTHORITIES_KEY))
            .signWith(key, SignatureAlgorithm.HS512)
            .setExpiration(validity)
            .compact();
    }

    public String createRefreshToken(User user, Authentication authentication) {
        log.info("Entered createRefreshToken in TokenProvider with authentication: {}.", authentication);

        String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        Date validity = new Date(now + this.refreshTokenValidityInMilliseconds);

        return Jwts
            .builder()
            .claim(JWT_CLAIM_ID_USER, user.getIdUser())
            .claim(JWT_CLAIM_FULL_NAME, user.getFullName())
            .claim(JWT_CLAIM_USERNAME, user.getUsername())
            .claim(JWT_CLAIM_EMAIL_ADDRESS, user.getEmail())
            .claim(AUTHORITIES_KEY, authorities)
            .signWith(key, SignatureAlgorithm.HS512)
            .setExpiration(validity)
            .compact();
    }

    public Authentication getAuthentication(String token) {
        log.info("Entered getAuthentication in TokenProvider with token: {}.", token);

        Claims claims = getClaims(token);

        Collection<? extends GrantedAuthority> authorities = Arrays
            .stream(claims.get(AUTHORITIES_KEY).toString().split(","))
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());

        org.springframework.security.core.userdetails.User principal = new org.springframework.security.core.userdetails.User(claims.get(JWT_CLAIM_USERNAME).toString(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public Claims getClaims(String token) {
        log.info("Entered getClaims in TokenProvider with token: {}.", token);

        return jwtParser
            .parseClaimsJws(token)
            .getBody();
    }

    public Long getCurrentUserId() {
        log.info("Entered getCurrentUserId in TokenProvider.");

        Claims claims = getClaims(
            SecurityUtils
                .getCurrentUserJWT()
                .orElseThrow(() -> new BadRequestException(UNKNOWN_JWT_TOKEN))
        );

        return claims.get(JWT_CLAIM_ID_USER, Long.class);
    }

    public boolean isCurrentUserAdmin() {
        log.info("Entered isCurrentUserAdmin in TokenProvider.");

        Claims claims = getClaims(
            SecurityUtils
                .getCurrentUserJWT()
                .orElseThrow(() -> new BadRequestException(UNKNOWN_JWT_TOKEN))
        );

        return claims.get(AUTHORITIES_KEY, String.class).equals(ADMIN);
    }

    public boolean validateToken(String token) {
        log.info("Entered validateToken in TokenProvider with token: {}.", token);

        try {
            jwtParser.parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException ex) {
            log.info(INVALID_JWT_TOKEN);
        }
        return false;
    }
}
