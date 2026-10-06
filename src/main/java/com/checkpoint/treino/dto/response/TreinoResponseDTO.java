package com.checkpoint.treino.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreinoResponseDTO {

    private Long id;
    private String nome;
    private LocalDate dataTreino;
    private Integer duracaoMinutos;
    private String tipo;
    private String nivel;
    private String observacoes;
}
