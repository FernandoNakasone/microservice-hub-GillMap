package br.com.GillMap.ms.regioes.service;

import br.com.GillMap.ms.regioes.dto.RegiaoRequestDTO;
import br.com.GillMap.ms.regioes.dto.RegiaoResponseDTO;
import br.com.GillMap.ms.regioes.entities.Regiao;
import br.com.GillMap.ms.regioes.entities.Situacao;
import br.com.GillMap.ms.regioes.exceptions.ResourceNotFoundException;
import br.com.GillMap.ms.regioes.repositories.RegiaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RegiaoService {

    @Autowired
    private RegiaoRepository regiaoRepository;

    @Transactional(readOnly = true)
    public List<RegiaoResponseDTO> findAllRegioes(){
        return regiaoRepository.findAll().stream().map(RegiaoResponseDTO :: new).toList();
    }

    @Transactional(readOnly = true)
    public RegiaoResponseDTO findRegiaoById(Long id){

        Regiao regiao = regiaoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado. id:" + id)
        );

        return new RegiaoResponseDTO(regiao);
    }

    @Transactional
    public RegiaoResponseDTO saveRegiao(RegiaoRequestDTO regiaoDTO) {

        Regiao regiao = new Regiao();
        mapDtoToRegiao(regiaoDTO, regiao);
        regiao.setSituacao(Situacao.LIMPO);
        regiao = regiaoRepository.save(regiao);
        return new RegiaoResponseDTO(regiao);
    }

    @Transactional
    public RegiaoResponseDTO updateRegiao(Long id, RegiaoRequestDTO regiaoDTO){

        try {
            Regiao regiao = regiaoRepository.getReferenceById(id);

            mapDtoToRegiao(regiaoDTO, regiao);
            regiao = regiaoRepository.save(regiao);
            return new RegiaoResponseDTO(regiao);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Recurso não encontrado. id:" + id);
        }

    }

    @Transactional
    public void deleteRegiaoById(Long id){
        if (!regiaoRepository.existsById(id)){
            throw new ResourceNotFoundException("Recurso não encontrado. Id: " + id);
        }

        regiaoRepository.deleteById(id);

    }


    @Transactional
    public void atualizarSituacao(Long id, Situacao situacao) {

        Optional<Regiao> regiao = regiaoRepository.findById(id);

        if (regiao.isEmpty()) {
            throw new ResourceNotFoundException("Recurso não encontrado. Id: " + id);
        }

        regiao.get().setSituacao(situacao);
        regiaoRepository.save(regiao.get());
    }


    private void mapDtoToRegiao(RegiaoRequestDTO regiaoDTO, Regiao regiao) {
        regiao.setNome(regiaoDTO.getNome());
        regiao.setCidade(regiaoDTO.getCidade());
        regiao.setEstado(regiaoDTO.getEstado());
        regiao.setLatitude(regiaoDTO.getLatitude());
        regiao.setLongitude(regiaoDTO.getLongitude());

    }

}
