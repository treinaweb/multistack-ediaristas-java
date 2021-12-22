package br.com.treinaweb.ediaristas.core.services.token.providers;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.treinaweb.ediaristas.core.services.token.adapters.TokenService;
import br.com.treinaweb.ediaristas.core.services.token.exceptions.TokenServiceException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JjwtService implements TokenService {

    @Value("${br.com.treinaweb.ediaristas.access.key}")
    private String accessKey;

    @Value("${br.com.treinaweb.ediaristas.access.expiration}")
    private int accessExpiration;

    @Value("${br.com.treinaweb.ediaristas.refresh.key}")
    private String refreshKey;

    @Value("${br.com.treinaweb.ediaristas.refresh.expiration}")
    private int refreshExpiration;

    @Override
    public String gerarAccessToken(String subject) {
        return gerarToken(accessKey, accessExpiration, subject);
    }

    @Override
    public String getSubjetDoAccessToken(String accessToken) {
        var claims = getClaims(accessToken, accessKey);

        return claims.getSubject();
    }

    @Override
    public String gerarRefreshToken(String subject) {
        return gerarToken(refreshKey, refreshExpiration, subject);
    }

    @Override
    public String getSubjectDoRefreshToken(String refreshToken) {
        var claims = getClaims(refreshToken, refreshKey);

        return claims.getSubject();
    }

    private String gerarToken(String signKey, int expiration, String subject) {
        var claims = new HashMap<String, Object>();

        var dataHoraAtual = Instant.now();
        var dataHoraExpiracao = dataHoraAtual.plusSeconds(expiration);

        return Jwts.builder()
            .setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(new Date(dataHoraAtual.toEpochMilli()))
            .setExpiration(new Date(dataHoraExpiracao.toEpochMilli()))
            .signWith(SignatureAlgorithm.HS512, signKey)
            .compact();
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
