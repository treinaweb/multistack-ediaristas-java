package br.com.treinaweb.ediaristas.core.services.token.providers;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import br.com.treinaweb.ediaristas.core.services.token.adapters.TokenService;
import br.com.treinaweb.ediaristas.core.services.token.exceptions.TokenServiceException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JjwtService implements TokenService {

    @Override
    public String gerarAccessToken(String subject) {
        var claims = new HashMap<String, Object>();

        var dataHoraAtual = Instant.now();
        var dataHoraExpiracao = dataHoraAtual.plusSeconds(30);

        return Jwts.builder()
            .setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(new Date(dataHoraAtual.toEpochMilli()))
            .setExpiration(new Date(dataHoraExpiracao.toEpochMilli()))
            .signWith(SignatureAlgorithm.HS512, "chave_access_token")
            .compact();
    }

    @Override
    public String getSubjetDoAccessToken(String accessToken) {
        var claims = getClaims(accessToken, "chave_access_token");

        return claims.getSubject();
    }

    private Claims getClaims(String token, String signKey) {
        try {
            return tryGetClaims(token, signKey);
        } catch (JwtException exception) {
            throw new TokenServiceException(exception.getLocalizedMessage());
        }
    }

    private Claims tryGetClaims(String token, String signKey) {
        return Jwts.parser()
            .setSigningKey(signKey)
            .parseClaimsJws(token)
            .getBody();
    }

}
