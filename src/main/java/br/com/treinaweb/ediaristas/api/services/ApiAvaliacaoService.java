package br.com.treinaweb.ediaristas.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.treinaweb.ediaristas.api.dtos.requests.AvaliacaoRequest;
import br.com.treinaweb.ediaristas.api.dtos.responses.MensagemResponse;
import br.com.treinaweb.ediaristas.api.mappers.ApiAvaliacaoMapper;
import br.com.treinaweb.ediaristas.core.exceptions.DiariaNaoEncontradaException;
import br.com.treinaweb.ediaristas.core.models.Avaliacao;
import br.com.treinaweb.ediaristas.core.models.Diaria;
import br.com.treinaweb.ediaristas.core.models.Usuario;
import br.com.treinaweb.ediaristas.core.repositories.AvaliacaoRepository;
import br.com.treinaweb.ediaristas.core.repositories.DiariaRepository;
import br.com.treinaweb.ediaristas.core.utils.SecurityUtils;
import br.com.treinaweb.ediaristas.core.validators.AvaliacaoValidator;

@Service
public class ApiAvaliacaoService {

    @Autowired
    private AvaliacaoRepository repository;

    @Autowired
    private SecurityUtils securityUtils;

    @Autowired
    private DiariaRepository diariaRepository;

    @Autowired
    private ApiAvaliacaoMapper mapper;

    @Autowired
    private AvaliacaoValidator validator;

    public MensagemResponse avaliarDiaria(AvaliacaoRequest request, Long id) {
        var diaria = buscarDiariaPorId(id);
        var avaliador = securityUtils.getUsuarioLogado();
        var model = mapper.toModel(request);
        model.setAvaliador(avaliador);
        model.setDiaria(diaria);
        model.setVisibilidade(true);
        model.setAvaliado(getAvaliado(model));

        validator.validar(model);

        repository.save(model);

        return new MensagemResponse("Avaliação realizada com sucesso!");
    }

    private Usuario getAvaliado(Avaliacao model) {
        if (model.getAvaliador().isCliente()) {
            return model.getDiaria().getDiarista();
        }
        return model.getDiaria().getCliente();
    }

    private Diaria buscarDiariaPorId(Long id) {
        var mensagem = String.format("Diária com id %d não encontrada", id);
        return diariaRepository.findById(id)
            .orElseThrow(() -> new DiariaNaoEncontradaException(mensagem));
    }

}
