package br.com.treinaweb.ediaristas.core.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.treinaweb.ediaristas.core.enums.DiariaStatus;
import br.com.treinaweb.ediaristas.core.models.Diaria;
import br.com.treinaweb.ediaristas.core.repositories.DiariaRepository;
import br.com.treinaweb.ediaristas.core.services.diaristaindice.adapters.DiaristaIndiceService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ScheduledTask {

    @Autowired
    private DiariaRepository diariaRepository;

    @Autowired
    private DiaristaIndiceService diaristaIndiceService;

    @Scheduled(cron = "0 0/5 * * * ?")
    @Transactional(readOnly = false)
    public void selecionarDiaristaDaDiaria() {
        log.info("Iniciada task de seleção de diaristas para diárias aptas");

        var diariasAptasParaSelecao = diariaRepository.getAptasParaSelecaoDeDiarista();
        diariasAptasParaSelecao.stream().forEach(this::selecionarDiarista);

        log.info("Task de seleção de diarista finalizada");
    }

    private void selecionarDiarista(Diaria diaria) {
        log.info("Selecionando melhor diárista para diária de id " + diaria.getId().toString());
        var melhorDiarista = diaristaIndiceService.selecionarMelhorDiarista(diaria);
        diaria.setDiarista(melhorDiarista);
        diaria.setStatus(DiariaStatus.CONFIRMADO);
        diariaRepository.save(diaria);
        log.info("Selecionado o diárista de id " + melhorDiarista.getId().toString());
    }

}
