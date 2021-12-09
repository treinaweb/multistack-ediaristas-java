package br.com.treinaweb.ediaristas.core.services.consultaendereco.adapters;

import br.com.treinaweb.ediaristas.core.services.consultaendereco.dtos.EnderecoResponse;
import br.com.treinaweb.ediaristas.core.services.consultaendereco.exceptions.EnderecoServiceException;

public interface EnderecoService {

    EnderecoResponse buscarEnderecoPorCep(String cep) throws EnderecoServiceException;

}
