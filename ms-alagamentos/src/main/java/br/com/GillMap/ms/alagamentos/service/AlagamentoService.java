package br.com.GillMap.ms.alagamentos.service;

import br.com.GillMap.ms.alagamentos.client.RegiaoClient;
import br.com.GillMap.ms.alagamentos.dto.AlagamentoRequestDTO;
import br.com.GillMap.ms.alagamentos.dto.AlagamentoResponseDTO;
import br.com.GillMap.ms.alagamentos.entities.Alagamento;
import br.com.GillMap.ms.alagamentos.exceptions.ResourceNotFoundException;
import br.com.GillMap.ms.alagamentos.repositories.AlagamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AlagamentoService {

    @Autowired
    private AlagamentoRepository alagamentoRepository;

    @Autowired
    private RegiaoClient regiaoClient;

    @Transactional(readOnly = true)
    public List<AlagamentoResponseDTO> findAllAlagamentos() {
        return alagamentoRepository.findAll().stream().map(AlagamentoResponseDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public AlagamentoResponseDTO findAlagamentoById(Long id) {

        Alagamento alagamento = alagamentoRepository.findById(id).orElseThrow(
                () ->  new ResourceNotFoundException("Recurso não encontrado. id: " + id)
        );

        return new AlagamentoResponseDTO(alagamento);
    }

    @Transactional
    public AlagamentoResponseDTO saveAlagamento(AlagamentoRequestDTO alagamentoDTO) {

        Alagamento alagamento = new Alagamento();
        mapDtoToAlagamento(alagamentoDTO, alagamento);
        alagamento.setDataHora(LocalDateTime.now());

        alagamento = alagamentoRepository.save(alagamento);
        atualizarSituacao(alagamento.getRegiaoId(), alagamento.getAlturaAguaCm());
        return new AlagamentoResponseDTO(alagamento);
    }

    @Transactional
    public AlagamentoResponseDTO updateAlagamento(Long id, AlagamentoRequestDTO alagamentoDTO) {

        Alagamento alagamento = alagamentoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado. id: " + id)
        );

        mapDtoToAlagamento(alagamentoDTO, alagamento);
        alagamento = alagamentoRepository.save(alagamento);
        atualizarSituacao(alagamento.getRegiaoId(), alagamento.getAlturaAguaCm());
        return new AlagamentoResponseDTO(alagamento);
    }

    @Transactional
    public void deleteAlagamentoById(Long id) {

        if (!alagamentoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado. id: " + id);
        }

        alagamentoRepository.deleteById(id);
    }

    private void atualizarSituacao(Long reigaoId, Double alturaAguaCm){

        if (alturaAguaCm == 0){
            regiaoClient.definirLimpo(reigaoId);
        } else if (alturaAguaCm <= 20) {
            regiaoClient.definirLeve(reigaoId);
        } else if (alturaAguaCm <= 40) {
            regiaoClient.definirMedio(reigaoId);
        } else{
            regiaoClient.definirGrave(reigaoId);
        }

    }

    private void mapDtoToAlagamento(AlagamentoRequestDTO alagamentoDTO, Alagamento alagamento) {
        alagamento.setRegiaoId(alagamentoDTO.getRegiaoId());
        alagamento.setEndereco(alagamentoDTO.getEndereco());
        alagamento.setDescricao(alagamentoDTO.getDescricao());
        alagamento.setAlturaAguaCm(alagamentoDTO.getAlturaAguaCm());
    }
}
