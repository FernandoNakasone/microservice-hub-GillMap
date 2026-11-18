package br.com.GillMap.ms.regioes.dto;

import br.com.GillMap.ms.regioes.entities.Regiao;
import br.com.GillMap.ms.regioes.entities.Situacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegiaoResponseDTO {

    private Long id;
    private String nome;
    private String cidade;
    private String estado;
    private Double latitude;
    private Double longitude;
    private Situacao situacao;

    public RegiaoResponseDTO(Regiao regiao){
        id = regiao.getId();
        nome = regiao.getNome();
        cidade = regiao.getCidade();
        estado = regiao.getEstado();
        latitude = regiao.getLatitude();
        longitude = regiao.getLongitude();
        situacao = regiao.getSituacao();
    }

}


