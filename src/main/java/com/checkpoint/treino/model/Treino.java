package com.checkpoint.treino.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "treinos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Treino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "data_treino", nullable = false)
    private LocalDate dataTreino;

    @Column(name = "duracao_minutos", nullable = false)
    private Integer duracaoMinutos;

    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo; // Ex: Musculação, Cardio, Funcional, HIIT

    @Column(name = "nivel", nullable = false, length = 20)
    private String nivel; // Ex: Iniciante, Intermediário, Avançado

    @Column(name = "observacoes", length = 1000) // nullable = true
    private String observacoes;
}
