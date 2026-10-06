package com.checkpoint.treino.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExercicioResponseDTO {

    private Long id;
    private String nome;
    private String grupoMuscular;
    private Integer series;
    private Integer repeticoes;
    private Double cargaKg;
    private String descricao;
}
