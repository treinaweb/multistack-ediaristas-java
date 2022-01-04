package br.com.treinaweb.ediaristas.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.treinaweb.ediaristas.api.dtos.requests.DiariaRequest;
import br.com.treinaweb.ediaristas.api.dtos.responses.DiariaResponse;
import br.com.treinaweb.ediaristas.core.models.Diaria;
import br.com.treinaweb.ediaristas.core.models.Servico;
import br.com.treinaweb.ediaristas.core.repositories.ServicoRepository;

@Mapper(componentModel = "spring", uses = ApiUsuarioDiariaMapper.class)
public abstract class ApiDiariaMapper {

    @Autowired
    protected ServicoRepository servicoRepository;

    public static final ApiDiariaMapper INSTANCE = Mappers.getMapper(ApiDiariaMapper.class);

    public abstract Diaria toModel(DiariaRequest request);

    @Mapping(target = "status", source = "status.id")
    @Mapping(target = "nomeServico", source = "servico.nome")
    @Mapping(target = "servico", source = "servico.id")
    public abstract DiariaResponse toResponse(Diaria model);

    protected Servico longToServico(Long servicoId) {
        return servicoRepository.findById(servicoId)
            .orElseThrow(IllegalArgumentException::new);
    }

}
