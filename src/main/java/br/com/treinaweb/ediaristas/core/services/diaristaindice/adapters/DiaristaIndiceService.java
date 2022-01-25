package br.com.treinaweb.ediaristas.core.services.diaristaindice.adapters;

import br.com.treinaweb.ediaristas.core.models.Diaria;
import br.com.treinaweb.ediaristas.core.models.Usuario;

public interface DiaristaIndiceService {

    public Usuario selecionarMelhorDiarista(Diaria diaria);

}
