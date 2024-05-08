package com.crs.propostaweb.mapper;

import com.crs.propostaweb.DTO.PropostaRecordDTO;
import com.crs.propostaweb.DTO.PropostaResponseRecordDTO;
import com.crs.propostaweb.entity.Proposta;
import com.crs.propostaweb.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PropostaConverter implements PropostaRecordMapper{
    public Proposta convertDtoToProposta(PropostaRecordDTO propostaRequestDTO) {
        if (propostaRequestDTO == null) {
            return null;
        }
        Proposta proposta = new Proposta();

        proposta.setUsuario(propostaRequestDTOToUsuario(propostaRequestDTO));
        proposta.setIntegrada(true);
        proposta.setValorSolicitado(propostaRequestDTO.valorSolicitado());
        proposta.setPrazoPagamento(propostaRequestDTO.prazoPagamento());

      return  proposta;
    }

    public PropostaResponseRecordDTO convertPropostaToDto(Proposta proposta){
        if (proposta == null) {
            return null;
        }
        return new PropostaResponseRecordDTO(
                proposta.getId(),
                proposta.getUsuario().getNome(),
                proposta.getUsuario().getSobrenome(),
                proposta.getUsuario().getTelefone(),
                proposta.getUsuario().getCpf(),
                proposta.getUsuario().getRenda(),
                proposta.getValorSolicitado(),
                proposta.getPrazoPagamento(),
                proposta.getAprovado(),
                proposta.getObservacao()
        );
    }
    public List<PropostaResponseRecordDTO> covertListEntityToListDTO(Iterable<Proposta> propostas) {

        if(propostas == null){
            return  null;
        }

        List<PropostaResponseRecordDTO> list = new ArrayList<>();

        for(Proposta proposta : propostas){
            list.add(convertPropostaToDto(proposta));
        }

        return  list;
    }

    protected Usuario propostaRequestDTOToUsuario(PropostaRecordDTO propostaRequestDTO) {
        if (propostaRequestDTO == null) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setNome(propostaRequestDTO.nome());
        usuario.setSobrenome(propostaRequestDTO.sobrenome());
        usuario.setCpf(propostaRequestDTO.cpf());
        usuario.setTelefone(propostaRequestDTO.telefone());
        usuario.setRenda(propostaRequestDTO.renda());

        return usuario;

    }
}
