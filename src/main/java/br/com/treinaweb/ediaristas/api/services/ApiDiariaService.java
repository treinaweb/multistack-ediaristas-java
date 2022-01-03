package br.com.treinaweb.ediaristas.api.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.treinaweb.ediaristas.api.dtos.requests.DiariaRequest;
import br.com.treinaweb.ediaristas.api.dtos.responses.DiariaResponse;
import br.com.treinaweb.ediaristas.api.mappers.ApiDiariaMapper;
import br.com.treinaweb.ediaristas.core.enums.DiariaStatus;
import br.com.treinaweb.ediaristas.core.models.Diaria;
import br.com.treinaweb.ediaristas.core.repositories.DiariaRepository;
import br.com.treinaweb.ediaristas.core.utils.SecurityUtils;
import br.com.treinaweb.ediaristas.core.validators.DiariaValidator;

@Service
public class ApiDiariaService {

    @Autowired
    private DiariaRepository repository;

    @Autowired
    private ApiDiariaMapper mapper;

    @Autowired
    private DiariaValidator validator;

    @Autowired
    private SecurityUtils securityUtils;

    public DiariaResponse cadastrar(DiariaRequest request) {
        var model = mapper.toModel(request);

        model.setValorComissao(calcularComissao(model));
        model.setCliente(securityUtils.getUsuarioLogado());
        model.setStatus(DiariaStatus.SEM_PAGAMENTO);

        validator.validar(model);

        var modelCadastrado = repository.save(model);

        return mapper.toResponse(modelCadastrado);
    }

    private BigDecimal calcularComissao(Diaria model) {
        var servico = model.getServico();
        var preco = model.getPreco();
        var procentagemComissao = servico.getProcentagemComissao();
        var bigDecimal100 = new BigDecimal(100);

        return preco.multiply(procentagemComissao.divide(bigDecimal100)).setScale(2);
    }

}
