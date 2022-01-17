package br.com.treinaweb.ediaristas.core.services.consultacidade.adapters;

import br.com.treinaweb.ediaristas.core.services.consultacidade.dtos.CidadeResponse;
import br.com.treinaweb.ediaristas.core.services.consultacidade.exceptions.ConsultaCidadeServiceException;

public interface ConsultaCidadeService {

    CidadeResponse buscarCidadePorCodigoIbge(String codigoIbge) throws ConsultaCidadeServiceException;

}
