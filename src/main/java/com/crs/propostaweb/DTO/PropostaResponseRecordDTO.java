package com.crs.propostaweb.DTO;

public record PropostaResponseRecordDTO(Long id, String nome, String sobrenome, String telefone, String cpf, String renda,
                                        Double valorSolicitado, int prazoPagamento, Boolean aprovado, String observacao) {

}
