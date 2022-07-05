package hr.pgalina.chain_reaction.security.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JWTTokenDto {

    private String accessToken;

    private String refreshToken;
}
