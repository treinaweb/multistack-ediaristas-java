package br.com.treinaweb.ediaristas.api.mappers;

import java.util.stream.Stream;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.treinaweb.ediaristas.api.dtos.requests.UsuarioRequest;
import br.com.treinaweb.ediaristas.core.enums.TipoUsuario;
import br.com.treinaweb.ediaristas.core.models.Usuario;

@Mapper(componentModel = "spring")
public interface ApiUsuarioMapper {

    ApiUsuarioMapper INSTANCE = Mappers.getMapper(ApiUsuarioMapper.class);

    @Mapping(target = "senha", source = "password")
    Usuario toModel(UsuarioRequest request);

    default TipoUsuario integerToTipoUsuario(Integer valor) {
        return Stream.of(TipoUsuario.values())
            .filter(tipoUsuario -> tipoUsuario.getId().equals(valor))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Tipo Usuário inválido"));
    }

}
