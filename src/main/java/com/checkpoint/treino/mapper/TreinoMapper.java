package com.checkpoint.treino.mapper;

import com.checkpoint.treino.dto.request.TreinoRequestDTO;
import com.checkpoint.treino.dto.response.TreinoResponseDTO;
import com.checkpoint.treino.model.Treino;
import org.springframework.stereotype.Component;

@Component
public class TreinoMapper {

    public Treino toEntity(TreinoRequestDTO dto) {
        Treino treino = new Treino();
        treino.setNome(dto.getNome());
        treino.setDataTreino(dto.getDataTreino());
        treino.setDuracaoMinutos(dto.getDuracaoMinutos());
        treino.setTipo(dto.getTipo());
        treino.setNivel(dto.getNivel());
        treino.setObservacoes(dto.getObservacoes());
        return treino;
    }

    public TreinoResponseDTO toResponseDTO(Treino treino) {
        TreinoResponseDTO dto = new TreinoResponseDTO();
        dto.setId(treino.getId());
        dto.setNome(treino.getNome());
        dto.setDataTreino(treino.getDataTreino());
        dto.setDuracaoMinutos(treino.getDuracaoMinutos());
        dto.setTipo(treino.getTipo());
        dto.setNivel(treino.getNivel());
        dto.setObservacoes(treino.getObservacoes());
        return dto;
    }
}
