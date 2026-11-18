
package br.com.GillMap.ms.alagamentos.controller;

import br.com.GillMap.ms.alagamentos.dto.AlagamentoRequestDTO;
import br.com.GillMap.ms.alagamentos.dto.AlagamentoResponseDTO;
import br.com.GillMap.ms.alagamentos.service.AlagamentoService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/alagamentos")
public class AlagamentoController {

    @Autowired
    private AlagamentoService alagamentoService;

    @GetMapping
    public ResponseEntity<List<AlagamentoResponseDTO>> getAllAlagamento() {
        List<AlagamentoResponseDTO> list = alagamentoService.findAllAlagamentos();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlagamentoResponseDTO> getAlagamentoById(@PathVariable Long id) {
        AlagamentoResponseDTO alagamentoDto = alagamentoService.findAlagamentoById(id);
        return ResponseEntity.ok(alagamentoDto);
    }

    @PostMapping
    @CircuitBreaker(name = "atualizarRegiao",
            fallbackMethod = "fallbackCriarAlagamento")
    public ResponseEntity<AlagamentoResponseDTO> createAlagamento(
            @RequestBody @Valid AlagamentoRequestDTO alagamentoDTO) {

        AlagamentoResponseDTO alagamento = alagamentoService.saveAlagamento(alagamentoDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(alagamento.getId())
                .toUri();

        return ResponseEntity.created(uri).body(alagamento);
    }

    public ResponseEntity<AlagamentoResponseDTO> fallbackCriarAlagamento(
            AlagamentoRequestDTO alagamentoDTO, Throwable e) {

        log.error("Falha ao cadastrar alagamento: {}", e.getMessage());

        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
    }

    @PutMapping("/{id}")
    @CircuitBreaker(name = "atualizarRegiao",
            fallbackMethod = "fallbackAtualizarAlagamento")
    public ResponseEntity<AlagamentoResponseDTO> updateAlagamento(
            @PathVariable Long id,
            @Valid @RequestBody AlagamentoRequestDTO alagamentoDTO) {

        AlagamentoResponseDTO alagamento = alagamentoService.updateAlagamento(id, alagamentoDTO);

        return ResponseEntity.ok(alagamento);
    }

    public ResponseEntity<AlagamentoResponseDTO> fallbackAtualizarAlagamento(
            Long id, AlagamentoRequestDTO alagamentoDTO, Throwable e) {

        log.error("Falha ao atualizar alagamento {}: {}", id, e.getMessage());

        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlagamento(@PathVariable Long id) {
        alagamentoService.deleteAlagamentoById(id);
        return ResponseEntity.noContent().build();
    }
}
