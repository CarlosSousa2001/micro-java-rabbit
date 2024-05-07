package com.crs.propostaweb.DTO;

import com.crs.propostaweb.entity.Usuario;

public record PropostaRecordDTO(String nome, String sobrenome, String cpf, String telefone, String renda, double valorSolicitado, int prazoPagamento) {}