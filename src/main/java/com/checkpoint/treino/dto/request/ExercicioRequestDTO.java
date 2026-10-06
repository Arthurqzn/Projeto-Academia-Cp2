package com.checkpoint.treino.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExercicioRequestDTO {

    @NotBlank @Size(max = 100)
    private String nome;

    @NotBlank @Size(max = 50)
    private String grupoMuscular;

    @NotNull @Positive
    private Integer series;

    @NotNull @Positive
    private Integer repeticoes;

    @PositiveOrZero
    private Double cargaKg;

    @Size(max = 1000)
    private String descricao;
}
