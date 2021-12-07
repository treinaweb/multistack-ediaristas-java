package br.com.treinaweb.ediaristas.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.treinaweb.ediaristas.api.dtos.responses.DiaristaLocalidadeResponse;
import br.com.treinaweb.ediaristas.core.models.Usuario;

@Mapper(componentModel = "spring")
public interface ApiDiaristaMapper {

    ApiDiaristaMapper INSTANCE = Mappers.getMapper(ApiDiaristaMapper.class);

    @Mapping(target = "fotoUsuario", source = "fotoUsuario.url")
    DiaristaLocalidadeResponse toDiaristaLocalidadeResponse(Usuario model);

}
