package com.crs.propostaweb.controller;

import com.crs.propostaweb.DTO.PropostaRecordDTO;
import com.crs.propostaweb.DTO.PropostaResponseRecordDTO;
import com.crs.propostaweb.service.PropostaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/proposta")
public class PropostaController {

    private PropostaService propostaService;

    @PostMapping
    public ResponseEntity<PropostaResponseRecordDTO> criar(@RequestBody PropostaRecordDTO requestDTO){
      PropostaResponseRecordDTO response =  propostaService.criar(requestDTO);
       return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
               .path("/{id}")
               .buildAndExpand(response.id())
               .toUri()).body(response);
    }

    @GetMapping
    public ResponseEntity<List<PropostaResponseRecordDTO>> obterPropostas(){
     return ResponseEntity.ok().body(propostaService.obterPropostas());
    }

}
