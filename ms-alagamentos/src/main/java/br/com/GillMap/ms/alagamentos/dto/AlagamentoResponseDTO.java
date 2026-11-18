package br.com.GillMap.ms.alagamentos.dto;

import br.com.GillMap.ms.alagamentos.entities.Alagamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AlagamentoResponseDTO {

    private Long id;
    private Long regiaoId;
    private String endereco;
    private String descricao;
    private Double alturaAguaCm;
    private LocalDateTime dataHora;

    public AlagamentoResponseDTO(Alagamento alagamento) {
        this.id = alagamento.getId();
        this.regiaoId = alagamento.getRegiaoId();
        this.endereco = alagamento.getEndereco();
        this.descricao = alagamento.getDescricao();
        this.alturaAguaCm = alagamento.getAlturaAguaCm();
        this.dataHora = alagamento.getDataHora();
    }
}
