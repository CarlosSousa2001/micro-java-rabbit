package com.crs.propostaweb.agendador;

import com.crs.propostaweb.entity.Proposta;
import com.crs.propostaweb.repository.PropostaRepository;
import com.crs.propostaweb.service.NotificacaoRabbitService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class PropostaSemIntagracao {

    private final String exchange;
    private final PropostaRepository propostaRepository;
    private final NotificacaoRabbitService notificacaoRabbitService;

    public PropostaSemIntagracao(@Value("${rabbitmq.proposta-pendente.exchange}") String exchange, PropostaRepository propostaRepository, NotificacaoRabbitService notificacaoRabbitService) {
        this.exchange = exchange;
        this.propostaRepository = propostaRepository;
        this.notificacaoRabbitService = notificacaoRabbitService;
    }

    @Scheduled(fixedDelay = 10, timeUnit = TimeUnit.SECONDS)
    // quando minha aplicação subir esse meteodo ja é chamado ja que a classe é um @component
    // após os fluxo normal de execução, essa função sera chamada depois de 10 segundos de novo
    public void buscarPropostaSemIntegracao(){
        propostaRepository.findAllByIntegradaIsFalse().forEach(proposta -> {
            try {
                notificacaoRabbitService.notificar(proposta, exchange);
                atualizarProposta(proposta);
            } catch (RuntimeException ex){
                System.out.println("---------Minha exceção do erro ao tentar enviar de novo a msg-----------" + ex);
            }
        });
    }

    private void atualizarProposta(Proposta proposta){
        proposta.setIntegrada(true);
        propostaRepository.save(proposta);
    }

}
