package com.checkpoint.treino.config;

import com.checkpoint.treino.model.Exercicio;
import com.checkpoint.treino.model.Treino;
import com.checkpoint.treino.repository.ExercicioRepository;
import com.checkpoint.treino.repository.TreinoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;

/** Insere alguns dados de exemplo no SQL Server (somente profile default e se as tabelas estiverem vazias). */
@Configuration
@Profile("default")
public class DataLoader {

    @Bean
    CommandLineRunner seed(TreinoRepository treinos, ExercicioRepository exercicios) {
        return args -> {
            if (treinos.count() == 0) {
                treinos.save(new Treino(null, "Treino A - Peito e Tríceps", LocalDate.now(), 60,
                        "Musculação", "Intermediário", "Foco em hipertrofia"));
                treinos.save(new Treino(null, "Treino B - Costas e Bíceps", LocalDate.now().plusDays(1), 55,
                        "Musculação", "Intermediário", null));
            }
            if (exercicios.count() == 0) {
                exercicios.save(new Exercicio(null, "Supino Reto", "Peitoral", 4, 10, 60.0, null));
                exercicios.save(new Exercicio(null, "Flexão de Braço", "Peitoral", 3, 15, null,
                        "Exercício com peso corporal"));
            }
        };
    }
}
