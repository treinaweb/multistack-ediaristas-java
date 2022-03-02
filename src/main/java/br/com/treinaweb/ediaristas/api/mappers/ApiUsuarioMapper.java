package br.com.treinaweb.ediaristas.api.mappers;

import java.util.stream.Stream;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.treinaweb.ediaristas.api.dtos.requests.UsuarioRequest;
import br.com.treinaweb.ediaristas.api.dtos.responses.UsuarioCadastroResponse;
import br.com.treinaweb.ediaristas.api.dtos.responses.UsuarioResponse;
import br.com.treinaweb.ediaristas.core.enums.TipoUsuario;
import br.com.treinaweb.ediaristas.core.models.Usuario;

@Mapper(componentModel = "spring")
public interface ApiUsuarioMapper {

    ApiUsuarioMapper INSTANCE = Mappers.getMapper(ApiUsuarioMapper.class);

    @Mapping(target = "senha", source = "password")
    @Mapping(target = "fotoDocumento", ignore = true)
    Usuario toModel(UsuarioRequest request);

    @Mapping(target = "tipoUsuario", source = "tipoUsuario.id")
    @Mapping(target = "fotoUsuario", source = "fotoUsuario.url")
    UsuarioResponse toResponse(Usuario model);

    @Mapping(target = "tipoUsuario", source = "tipoUsuario.id")
    @Mapping(target = "fotoUsuario", source = "fotoUsuario.url")
    UsuarioCadastroResponse toCadastroResponse(Usuario model);

    default TipoUsuario integerToTipoUsuario(Integer valor) {
        return Stream.of(TipoUsuario.values())
            .filter(tipoUsuario -> tipoUsuario.getId().equals(valor))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Tipo Usuário inválido"));
    }

}
