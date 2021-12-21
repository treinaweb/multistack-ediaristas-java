package br.com.treinaweb.ediaristas.core.services.token.providers;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import br.com.treinaweb.ediaristas.core.services.token.adapters.TokenService;
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
        var claims = Jwts.parser()
            .setSigningKey("chave_access_token")
            .parseClaimsJws(accessToken)
            .getBody();

        return claims.getSubject();
    }

}
