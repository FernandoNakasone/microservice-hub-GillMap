package br.com.GillMap.ms.regioes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RegiaoRequestDTO {

    @NotBlank(message = "Nome é requerido")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String nome;

    @NotBlank(message = "Cidade é requerido")
    @Size(min = 2, max = 100, message = "A cidade deve ter entre 2 e 100 caracteres")
    private String cidade;

    @NotBlank(message = "Estado é requerido")
    @Size(min = 2, max = 2, message = "O estado deve ter entre 2 caracteres")
    private String estado;

    @NotNull(message = "latitude não pode ser nulo")
    private Double latitude;

    @NotNull(message = "longitude não pode ser nulo")
    private Double longitude;
}
