package com.checkpoint.treino.controller;

import com.checkpoint.treino.dto.request.TreinoRequestDTO;
import com.checkpoint.treino.dto.response.TreinoResponseDTO;
import com.checkpoint.treino.mapper.TreinoMapper;
import com.checkpoint.treino.model.Treino;
import com.checkpoint.treino.repository.TreinoRepository;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/treinos")
@Tag(name = "Treinos", description = "Operações CRUD para Treinos")
public class TreinoController {

    @Autowired
    private TreinoRepository treinoRepository;

    @Autowired
    private TreinoMapper treinoMapper;

    // GET - Buscar todos os treinos
    @GetMapping
    @Operation(summary = "Listar todos os treinos")
    public ResponseEntity<List<TreinoResponseDTO>> findAll() {
        List<TreinoResponseDTO> treinos = treinoRepository.findAll()
                .stream()
                .map(treinoMapper::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(treinos);
    }

    // GET - Buscar treino por ID
    @GetMapping("/{id}")
    @Operation(summary = "Buscar treino por ID")
    public ResponseEntity<TreinoResponseDTO> findById(@PathVariable Long id) {
        Optional<Treino> treino = treinoRepository.findById(id);
        return treino.map(t -> ResponseEntity.ok(treinoMapper.toResponseDTO(t)))
                .orElse(ResponseEntity.notFound().build());
    }

    // POST - Criar novo treino
    @PostMapping
    @Operation(summary = "Criar novo treino")
    public ResponseEntity<TreinoResponseDTO> create(@Valid @RequestBody TreinoRequestDTO dto) {
        Treino treino = treinoMapper.toEntity(dto);
        Treino salvo = treinoRepository.save(treino);
        return ResponseEntity.status(HttpStatus.CREATED).body(treinoMapper.toResponseDTO(salvo));
    }

    // PUT - Atualizar treino
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar treino existente")
    public ResponseEntity<TreinoResponseDTO> update(@PathVariable Long id, @Valid @RequestBody TreinoRequestDTO dto) {
        Optional<Treino> optional = treinoRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Treino treino = optional.get();
        treino.setNome(dto.getNome());
        treino.setDataTreino(dto.getDataTreino());
        treino.setDuracaoMinutos(dto.getDuracaoMinutos());
        treino.setTipo(dto.getTipo());
        treino.setNivel(dto.getNivel());
        treino.setObservacoes(dto.getObservacoes());
        Treino salvo = treinoRepository.save(treino);
        return ResponseEntity.ok(treinoMapper.toResponseDTO(salvo));
    }

    // DELETE - Remover treino
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar treino por ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!treinoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        treinoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
