package com.crs.propostaweb.service;

import com.crs.propostaweb.DTO.PropostaRecordDTO;
import com.crs.propostaweb.DTO.PropostaResponseRecordDTO;
import com.crs.propostaweb.entity.Proposta;
import com.crs.propostaweb.mapper.PropostaRecordMapper;
import com.crs.propostaweb.repository.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropostaService {

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private PropostaRecordMapper propostaConverter;

    public PropostaResponseRecordDTO criar(PropostaRecordDTO requestDTO){
     Proposta proposta = propostaConverter.convertDtoToProposta(requestDTO);
     propostaRepository.save(proposta);
     return propostaConverter.convertPropostaToDto(proposta);
    }

    public List<PropostaResponseRecordDTO> obterPropostas() {

    return propostaConverter.covertListEntityToListDTO( propostaRepository.findAll());

    }
}
