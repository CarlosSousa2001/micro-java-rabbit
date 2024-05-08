package com.crs.propostaweb.service;

import com.crs.propostaweb.DTO.PropostaRecordDTO;
import com.crs.propostaweb.DTO.PropostaResponseRecordDTO;
import com.crs.propostaweb.entity.Proposta;
import com.crs.propostaweb.mapper.PropostaRecordMapper;
import com.crs.propostaweb.repository.PropostaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropostaService {

    private final String exchange;

    private PropostaRepository propostaRepository;

    private NotificacaoRabbitService notificacaoRabbitService;

    private PropostaRecordMapper propostaConverter;

    public PropostaService(@Value("${rabbitmq.proposta-pendente.exchange}") String exchange, PropostaRepository propostaRepository, NotificacaoRabbitService notificacaoRabbitService, PropostaRecordMapper propostaConverter) {
        this.exchange = exchange;
        this.propostaRepository = propostaRepository;
        this.notificacaoRabbitService = notificacaoRabbitService;
        this.propostaConverter = propostaConverter;
    }

    public PropostaResponseRecordDTO criar(PropostaRecordDTO requestDTO){
     Proposta proposta = propostaConverter.convertDtoToProposta(requestDTO);
     propostaRepository.save(proposta);

     notificarRabbitMQ(proposta);
     notificacaoRabbitService.notificar(proposta, exchange);

     return propostaConverter.convertPropostaToDto(proposta);
    }

    private void notificarRabbitMQ(Proposta proposta){
        try {
            notificacaoRabbitService.notificar(proposta, exchange);
        } catch (RuntimeException ex){
            proposta.setIntegrada(false);
            propostaRepository.save(proposta);
        }
    }

    public List<PropostaResponseRecordDTO> obterPropostas() {
        return propostaConverter.covertListEntityToListDTO( propostaRepository.findAll());
    }
}
