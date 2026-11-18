package br.com.GillMap.ms.alagamentos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
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
    @Positive(message = "O campo regiaoId deve ser um número maior que zero")
    @Schema(example = "1")
    private Long regiaoId;

    @NotBlank(message = "O campo endereco não pode ser em branco/vazio")
    @Size(min = 3,max = 150, message = "O campo endereco deve ter entre 3 e 150 caracteres")
    @Schema(example = "Rua da Consolação, 250")
    private String endereco;

    @NotBlank(message = "O campo descricao não pode ser em branco/vazio")
    @Size(min = 3, max = 500,  message = "O campo descricao deve ter entre 3 e 500 caracteres")
    @Schema(example = "Grande acúmulo de água após chuva intensa")
    private String descricao;

    @NotNull(message = "O campo alturaAguaCm não pode ser nulo")
    @PositiveOrZero(message = "O campo alturaAguaCm deve ser zero ou maior que zero")
    @Schema(example = "25.6")
    private Double alturaAguaCm;
}

