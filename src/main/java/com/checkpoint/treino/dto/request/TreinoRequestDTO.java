package com.checkpoint.treino.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreinoRequestDTO {

    @NotBlank @Size(max = 100)
    private String nome;

    @NotNull
    private LocalDate dataTreino;

    @NotNull @Positive
    private Integer duracaoMinutos;

    @NotBlank @Size(max = 50)
    private String tipo;

    @NotBlank @Size(max = 20)
    private String nivel;

    @Size(max = 1000)
    private String observacoes;
}
