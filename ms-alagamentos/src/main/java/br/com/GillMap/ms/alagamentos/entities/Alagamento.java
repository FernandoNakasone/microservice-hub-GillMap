package br.com.GillMap.ms.alagamentos.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_alagamento")
public class Alagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "regiao_id", nullable = false)
    private Long regiaoId;

    @Column(name = "endereco", nullable = false, length = 150)
    private String endereco;

    @Column(name = "descricao",nullable = false, length = 500)
    private String descricao;

    @Column(name = "alturaAguaCm",nullable = false)
    private Double alturaAguaCm;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;
}
