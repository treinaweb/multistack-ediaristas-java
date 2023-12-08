package br.com.treinaweb.ediaristas.api.handlers;

import java.io.IOException;
import java.time.LocalDateTime;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import br.com.treinaweb.ediaristas.api.dtos.responses.ErrorResponse;

@Component
public class TokenAccessDeniedHandler implements AccessDeniedHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void handle(
        HttpServletRequest request,
        HttpServletResponse response,
        AccessDeniedException accessDeniedException
    ) throws IOException, ServletException {
        var status = HttpStatus.FORBIDDEN;

        var errorResponse = ErrorResponse.builder()
            .status(status.value())
            .timestamp(LocalDateTime.now())
            .mensagem(accessDeniedException.getLocalizedMessage())
            .path(request.getRequestURI())
            .build();

        var json = objectMapper.writeValueAsString(errorResponse);

        response.setStatus(status.value());
        response.setHeader("Content-Type", "application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(json);
    }

}
