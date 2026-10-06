package com.checkpoint.treino.mapper;

import com.checkpoint.treino.dto.request.ExercicioRequestDTO;
import com.checkpoint.treino.dto.response.ExercicioResponseDTO;
import com.checkpoint.treino.model.Exercicio;
import org.springframework.stereotype.Component;

@Component
public class ExercicioMapper {

    public Exercicio toEntity(ExercicioRequestDTO dto) {
        Exercicio exercicio = new Exercicio();
        exercicio.setNome(dto.getNome());
        exercicio.setGrupoMuscular(dto.getGrupoMuscular());
        exercicio.setSeries(dto.getSeries());
        exercicio.setRepeticoes(dto.getRepeticoes());
        exercicio.setCargaKg(dto.getCargaKg());
        exercicio.setDescricao(dto.getDescricao());
        return exercicio;
    }

    public ExercicioResponseDTO toResponseDTO(Exercicio exercicio) {
        ExercicioResponseDTO dto = new ExercicioResponseDTO();
        dto.setId(exercicio.getId());
        dto.setNome(exercicio.getNome());
        dto.setGrupoMuscular(exercicio.getGrupoMuscular());
        dto.setSeries(exercicio.getSeries());
        dto.setRepeticoes(exercicio.getRepeticoes());
        dto.setCargaKg(exercicio.getCargaKg());
        dto.setDescricao(exercicio.getDescricao());
        return dto;
    }
}
