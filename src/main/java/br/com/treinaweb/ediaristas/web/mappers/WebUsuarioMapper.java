package br.com.treinaweb.ediaristas.web.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.treinaweb.ediaristas.core.models.Usuario;
import br.com.treinaweb.ediaristas.web.dtos.UsuarioCadastroForm;

@Mapper(componentModel = "spring")
public interface WebUsuarioMapper {

    WebUsuarioMapper INSTANCE = Mappers.getMapper(WebUsuarioMapper.class);

    Usuario toModel(UsuarioCadastroForm form);
    
}
