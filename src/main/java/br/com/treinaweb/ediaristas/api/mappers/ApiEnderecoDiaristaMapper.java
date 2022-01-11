package br.com.treinaweb.ediaristas.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.treinaweb.ediaristas.api.dtos.requests.EnderecoDiaristaRequest;
import br.com.treinaweb.ediaristas.api.dtos.responses.EndereciDiaristaResponse;
import br.com.treinaweb.ediaristas.core.models.EnderecoDiarista;

@Mapper(componentModel = "spring")
public interface ApiEnderecoDiaristaMapper {

    ApiEnderecoDiaristaMapper INSTANCE = Mappers.getMapper(ApiEnderecoDiaristaMapper.class);

    EnderecoDiarista toModel(EnderecoDiaristaRequest request);

    EndereciDiaristaResponse toResponse(EnderecoDiarista model);

}
