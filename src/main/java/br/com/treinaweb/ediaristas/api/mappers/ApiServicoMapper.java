package br.com.treinaweb.ediaristas.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.treinaweb.ediaristas.api.dtos.responses.ServicoResponse;
import br.com.treinaweb.ediaristas.core.models.Servico;

@Mapper(componentModel = "spring")
public interface ApiServicoMapper {

    ApiServicoMapper INSTANCE = Mappers.getMapper(ApiServicoMapper.class);

    @Mapping(target = "icone", source = "icone.nome")
    ServicoResponse toResponse(Servico model);

}
