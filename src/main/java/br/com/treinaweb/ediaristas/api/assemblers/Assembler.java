package br.com.treinaweb.ediaristas.api.assemblers;

import java.util.List;

public interface Assembler<R> {

    void adicionarLinks(R resource);

    void adicionarLinks(List<R> collectionResource);

}
