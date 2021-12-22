package br.com.treinaweb.ediaristas.core.services.token.adapters;

public interface TokenService {

    String gerarAccessToken(String subject);

    String getSubjetDoAccessToken(String accessToken);

    String gerarRefreshToken(String subject);

    String getSubjectDoRefreshToken(String refreshToken);

}
