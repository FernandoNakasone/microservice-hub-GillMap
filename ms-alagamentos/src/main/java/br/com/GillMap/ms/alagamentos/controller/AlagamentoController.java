package br.com.GillMap.ms.alagamentos.controller;

import br.com.GillMap.ms.alagamentos.dto.AlagamentoRequestDTO;
import br.com.GillMap.ms.alagamentos.dto.AlagamentoResponseDTO;
import br.com.GillMap.ms.alagamentos.service.AlagamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/alagamentos")
public class AlagamentoController {

    @Autowired
    private AlagamentoService alagamentoService;

    @GetMapping
    private ResponseEntity<List<AlagamentoResponseDTO>> getAllAlagamento(){
        List<AlagamentoResponseDTO> list = alagamentoService.findAllAlagamentos();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlagamentoResponseDTO> getAlagamentoById(@PathVariable Long id){

        AlagamentoResponseDTO alagamentoDto = alagamentoService.findAlagamentoById(id);

        return ResponseEntity.ok(alagamentoDto);
    }

    @PostMapping
    public ResponseEntity<AlagamentoResponseDTO> createAlagamento(@RequestBody @Valid AlagamentoRequestDTO alagamentoDTO){

        AlagamentoResponseDTO alagamento = alagamentoService.saveAlagamento(alagamentoDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand( alagamento.getId())
                .toUri();

        return ResponseEntity.created(uri).body(alagamento);

    }

    @PutMapping("/{id}")
    public ResponseEntity<AlagamentoResponseDTO> updateAlagamento(@PathVariable Long id, @Valid @RequestBody AlagamentoRequestDTO alagamentoDTO){


        AlagamentoResponseDTO alagamento = alagamentoService.updateAlagamento(id,alagamentoDTO);

        return ResponseEntity.ok(alagamento);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlagamento(@PathVariable Long id){

        alagamentoService.deleteAlagamentoById(id);

        return ResponseEntity.noContent().build();

    }

}
