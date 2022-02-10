package br.com.treinaweb.ediaristas.api.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.treinaweb.ediaristas.api.dtos.requests.CidadesAtendidasRequest;
import br.com.treinaweb.ediaristas.api.dtos.responses.CidadeAtendidaResponse;
import br.com.treinaweb.ediaristas.api.dtos.responses.MensagemResponse;
import br.com.treinaweb.ediaristas.api.mappers.ApiCidadeAtendidaMapper;
import br.com.treinaweb.ediaristas.core.exceptions.CidadeAtendidaNaoEncontradaException;
import br.com.treinaweb.ediaristas.core.models.CidadeAtendida;
import br.com.treinaweb.ediaristas.core.repositories.CidadeAtendidaRepository;
import br.com.treinaweb.ediaristas.core.repositories.UsuarioRepository;
import br.com.treinaweb.ediaristas.core.services.consultacidade.adapters.ConsultaCidadeService;
import br.com.treinaweb.ediaristas.core.utils.SecurityUtils;

@Service
public class ApiCidadesAtendidasService {

    @Autowired
    private SecurityUtils securityUtils;

    @Autowired
    private ApiCidadeAtendidaMapper mapper;

    @Autowired
    private CidadeAtendidaRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ConsultaCidadeService consultaCidadeService;

    public List<CidadeAtendidaResponse> listarCidadesAtendidas() {
        return securityUtils.getUsuarioLogado()
            .getCidadesAtendidas()
            .stream()
            .map(mapper::toResponse)
            .toList();
    }

    public MensagemResponse atualizarCidadesAtendidas(CidadesAtendidasRequest request) {
        var usuarioLogado = securityUtils.getUsuarioLogado();
        var cidadesAtendidas = new ArrayList<CidadeAtendida>();

        request.getCidades().forEach(cidadeAtendidaRequest -> {
            var codigoIbge = cidadeAtendidaRequest.getCodigoIbge();
            CidadeAtendida cidadeAtendida;
            try {
                cidadeAtendida = buscarCidadeAtendidaPorCodigoIbge(codigoIbge);
            } catch (CidadeAtendidaNaoEncontradaException exception) {
                cidadeAtendida = cadastrarCidadeAtendida(codigoIbge);
            }
            cidadesAtendidas.add(cidadeAtendida);
        });
        usuarioLogado.setCidadesAtendidas(cidadesAtendidas);
        usuarioRepository.save(usuarioLogado);
        return new MensagemResponse("Cidades atendidas atualizadas com sucesso!");
    }

    private CidadeAtendida cadastrarCidadeAtendida(String codigoIbge) {
        var cidade = consultaCidadeService.buscarCidadePorCodigoIbge(codigoIbge);
        var cidadeAtendida = new CidadeAtendida();
        cidadeAtendida.setCodigoIbge(codigoIbge);
        cidadeAtendida.setCidade(cidade.getCidade());
        cidadeAtendida.setEstado(cidade.getEstado());

        return repository.save(cidadeAtendida);
    }

    private CidadeAtendida buscarCidadeAtendidaPorCodigoIbge(String codigoIbge) {
        var mensagem = String.format("Cidade com código ibge %s não econtrada", codigoIbge);
        return repository.findByCodigoIbge(codigoIbge)
            .orElseThrow(() -> new CidadeAtendidaNaoEncontradaException(mensagem));
    }

}
