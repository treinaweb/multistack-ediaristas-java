package br.com.treinaweb.ediaristas.core.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.treinaweb.ediaristas.core.enums.DiariaStatus;
import br.com.treinaweb.ediaristas.core.models.Diaria;
import br.com.treinaweb.ediaristas.core.repositories.DiariaRepository;
import br.com.treinaweb.ediaristas.core.services.diaristaindice.adapters.DiaristaIndiceService;
import br.com.treinaweb.ediaristas.core.services.gatewaypagamento.adpaters.GatewayPagamentoService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ScheduledTask {

    @Autowired
    private DiariaRepository diariaRepository;

    @Autowired
    private DiaristaIndiceService diaristaIndiceService;

    @Autowired
    private GatewayPagamentoService gatewayPagamentoService;

    @Scheduled(cron = "0 0/5 * * * ?")
    @Transactional(readOnly = false)
    public void selecionarDiaristaDaDiaria() {
        log.info("Iniciada task de seleção de diaristas para diárias aptas");

        var diariasAptasParaSelecao = diariaRepository.getAptasParaSelecaoDeDiarista();
        diariasAptasParaSelecao.stream().forEach(this::selecionarDiarista);

        log.info("Task de seleção de diarista finalizada");
    }

    @Scheduled(cron = "0 3/5 * * * ?")
    @Transactional(readOnly = false)
    public void cancelarDiariasSemCandidatos() {
        log.info("Iniciada task de cancelamento de diárias sem candidatos");

        var diariasAptasParaCancelamento = diariaRepository.getAptasParaCancelamento();
        diariasAptasParaCancelamento.stream().forEach(this::cancelarDiaria);

        log.info("Finalizada task de cancelamento de diárias sem candidatos");
    }

    private void selecionarDiarista(Diaria diaria) {
        log.info("Selecionando melhor diárista para diária de id " + diaria.getId().toString());
        var melhorDiarista = diaristaIndiceService.selecionarMelhorDiarista(diaria);
        diaria.setDiarista(melhorDiarista);
        diaria.setStatus(DiariaStatus.CONFIRMADO);
        diariaRepository.save(diaria);
        log.info("Selecionado o diárista de id " + melhorDiarista.getId().toString());
    }

    private void cancelarDiaria(Diaria diaria) {
        log.info("Cancelando diária de id " + diaria.getId());
        if (diaria.isPago()) {
            log.info("Reembolsando pagamento da diária de id " + diaria.getId());
            gatewayPagamentoService.realizarEstornoTotal(diaria);
        }
        diaria.setStatus(DiariaStatus.CANCELADO);
        diariaRepository.save(diaria);
        log.info("Diária de id " + diaria.getId() + " cancelada com sucesso");
    }

}
