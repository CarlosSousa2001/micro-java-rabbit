package com.crs.propostaweb.mapper;

import com.crs.propostaweb.DTO.PropostaRecordDTO;
import com.crs.propostaweb.DTO.PropostaResponseRecordDTO;
import com.crs.propostaweb.entity.Proposta;

import java.util.List;

public interface PropostaRecordMapper {
    Proposta convertDtoToProposta(PropostaRecordDTO propostaRequestDTO);

    PropostaResponseRecordDTO convertPropostaToDto(Proposta proposta);

    List<PropostaResponseRecordDTO> covertListEntityToListDTO(Iterable<Proposta> propostas);
}
