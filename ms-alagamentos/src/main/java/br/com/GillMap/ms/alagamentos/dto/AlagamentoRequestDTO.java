package br.com.GillMap.ms.alagamentos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AlagamentoRequestDTO {

    @NotNull(message = "O campo regiao não pode ser nulo")
    private Long regiaoId;

    @NotBlank(message = "O campo endereco não pode ser em branco/vazio")
    @Size(min = 3,max = 150, message = "O campo endereco deve ter entre 3 e 150 caracteres")
    private String endereco;

    @NotBlank(message = "O campo descricao não pode ser em branco/vazio")
    @Size(min = 3, max = 500,  message = "O campo descricao deve ter entre 3 e 500 caracteres")
    private String descricao;

    @NotNull(message = "O campo alturaAguaCm não pode ser nulo")
    @PositiveOrZero(message = "O campo alturaAguaCm deve ser zero ou maior que zero")
    private Double alturaAguaCm;
}

