package br.com.treinaweb.ediaristas.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.treinaweb.ediaristas.api.dtos.responses.PagamentoResponse;
import br.com.treinaweb.ediaristas.api.mappers.ApiPagamentoMapper;
import br.com.treinaweb.ediaristas.core.enums.DiariaStatus;
import br.com.treinaweb.ediaristas.core.repositories.PagamentoRepository;
import br.com.treinaweb.ediaristas.core.utils.SecurityUtils;

@Service
public class ApiPagamentoService {

    @Autowired
    private PagamentoRepository repository;

    @Autowired
    private ApiPagamentoMapper mapper;

    @Autowired
    private SecurityUtils securityUtils;

    public List<PagamentoResponse> listarPagamentos() {
        var usuarioLogado = securityUtils.getUsuarioLogado();
        var status = List.of(
            DiariaStatus.CONCLUIDO,
            DiariaStatus.AVALIADO,
            DiariaStatus.TRANSFERIDO
        );

        return repository.findByDiariaDiaristaAndDiariaStatusIn(usuarioLogado, status)
            .stream()
            .map(mapper::toResponse)
            .toList();
    }

}
