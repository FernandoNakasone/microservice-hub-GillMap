package br.com.GillMap.ms.regioes.controller;

import br.com.GillMap.ms.regioes.dto.RegiaoRequestDTO;
import br.com.GillMap.ms.regioes.dto.RegiaoResponseDTO;
import br.com.GillMap.ms.regioes.entities.Situacao;
import br.com.GillMap.ms.regioes.service.RegiaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/regioes")
public class RegiaoController {

    @Autowired
    private RegiaoService regiaoService;

    @GetMapping
    private ResponseEntity<List<RegiaoResponseDTO>> getAllRegioes(){
        List<RegiaoResponseDTO> list = regiaoService.findAllRegioes();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegiaoResponseDTO> getRegiao(@PathVariable Long id){

        RegiaoResponseDTO pedidoDto = regiaoService.findRegiaoById(id);

        return ResponseEntity.ok(pedidoDto);
    }

    @PostMapping
    public ResponseEntity<RegiaoResponseDTO> createRegiao(@RequestBody @Valid RegiaoRequestDTO regiaoDTO){

        RegiaoResponseDTO regiao = regiaoService.saveRegiao(regiaoDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand( regiao.getId())
                .toUri();

        return ResponseEntity.created(uri).body(regiao);

    }

    @PutMapping("/{id}")
    public ResponseEntity<RegiaoResponseDTO> updateRegiao(@PathVariable Long id, @Valid @RequestBody RegiaoRequestDTO requestDTO){


        RegiaoResponseDTO regiao = regiaoService.updateRegiao(id,requestDTO);

        return ResponseEntity.ok(regiao);

    }

    @PutMapping("/{regiaoId}/alagamento/situacao")
    public void mudarSituacao(@PathVariable Long regiaoId, @Valid Situacao situacao){

        regiaoService.atualizarSituacao(regiaoId, situacao);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegiao(@PathVariable Long id){

        regiaoService.deleteRegiaoById(id);

        return ResponseEntity.noContent().build();

    }


}
