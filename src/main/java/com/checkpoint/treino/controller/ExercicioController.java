package com.checkpoint.treino.controller;

import com.checkpoint.treino.dto.request.ExercicioRequestDTO;
import com.checkpoint.treino.dto.response.ExercicioResponseDTO;
import com.checkpoint.treino.mapper.ExercicioMapper;
import com.checkpoint.treino.model.Exercicio;
import com.checkpoint.treino.repository.ExercicioRepository;
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
@RequestMapping("/exercicios")
@Tag(name = "Exercícios", description = "Operações CRUD para Exercícios")
public class ExercicioController {

    @Autowired
    private ExercicioRepository exercicioRepository;

    @Autowired
    private ExercicioMapper exercicioMapper;

    // GET - Buscar todos os exercícios
    @GetMapping
    @Operation(summary = "Listar todos os exercícios")
    public ResponseEntity<List<ExercicioResponseDTO>> findAll() {
        List<ExercicioResponseDTO> exercicios = exercicioRepository.findAll()
                .stream()
                .map(exercicioMapper::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(exercicios);
    }

    // GET - Buscar exercício por ID
    @GetMapping("/{id}")
    @Operation(summary = "Buscar exercício por ID")
    public ResponseEntity<ExercicioResponseDTO> findById(@PathVariable Long id) {
        Optional<Exercicio> exercicio = exercicioRepository.findById(id);
        return exercicio.map(e -> ResponseEntity.ok(exercicioMapper.toResponseDTO(e)))
                .orElse(ResponseEntity.notFound().build());
    }

    // POST - Criar novo exercício
    @PostMapping
    @Operation(summary = "Criar novo exercício")
    public ResponseEntity<ExercicioResponseDTO> create(@Valid @RequestBody ExercicioRequestDTO dto) {
        Exercicio exercicio = exercicioMapper.toEntity(dto);
        Exercicio salvo = exercicioRepository.save(exercicio);
        return ResponseEntity.status(HttpStatus.CREATED).body(exercicioMapper.toResponseDTO(salvo));
    }

    // PUT - Atualizar exercício
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar exercício existente")
    public ResponseEntity<ExercicioResponseDTO> update(@PathVariable Long id, @Valid @RequestBody ExercicioRequestDTO dto) {
        Optional<Exercicio> optional = exercicioRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Exercicio exercicio = optional.get();
        exercicio.setNome(dto.getNome());
        exercicio.setGrupoMuscular(dto.getGrupoMuscular());
        exercicio.setSeries(dto.getSeries());
        exercicio.setRepeticoes(dto.getRepeticoes());
        exercicio.setCargaKg(dto.getCargaKg());
        exercicio.setDescricao(dto.getDescricao());
        Exercicio salvo = exercicioRepository.save(exercicio);
        return ResponseEntity.ok(exercicioMapper.toResponseDTO(salvo));
    }

    // DELETE - Remover exercício
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar exercício por ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!exercicioRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        exercicioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
