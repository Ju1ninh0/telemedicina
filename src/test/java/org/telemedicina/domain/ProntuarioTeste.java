package org.telemedicina.domain;

import org.junit.jupiter.api.Test;
import org.telemedicina.domain.entities.Prontuario;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProntuarioTeste {

    @Test
    void ProntuarioValido(){
        Prontuario prontuario= new Prontuario(
                "Arthur",
                "Ana",
                "Masculino",
                "Rua la ele 1000",
                 LocalDate.of(2010,11,7),
                "Paciente Tarso com dor na face"
        );
        assertEquals("Arthur", prontuario.getNome());
        assertEquals("Ana", prontuario.getNomeMae());
        assertEquals("Masculino", prontuario.getSexo());
        assertEquals("Rua la ele 1000", prontuario.getEndereco());
        assertEquals(LocalDate.of(2010, 11, 7), prontuario.getDataNascimento());
        assertEquals("Paciente Tarso com dor na face", prontuario.getDescricao());
    }
    @Test
    void NomeNulo(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Prontuario(
                    null,
                    "Ana",
                    "Masculino",
                    "Rua la ele 1000",
                    LocalDate.of(2010,11,7),
                    "Paciente Tarso com dor na face"
            );
        });
    }
    @Test
    void NomeDaMaeNulo(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Prontuario(
                    "Arthur",
                    null,
                    "Masculino",
                    "Rua la ele 1000",
                    LocalDate.of(2010,11,7),
                    "Paciente Tarso com dor na face"
            );
        });
    }
    @Test
    void SexoNulo(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Prontuario(
                    "Arthur",
                    "Ana",
                    null,
                    "Rua la ele 1000",
                    LocalDate.of(2010,11,7),
                    "Paciente Tarso com dor na face"
            );
        });
    }
    @Test
    void EndereçoNulo(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Prontuario(
                    "Arthur",
                    "Ana",
                    "Masculino",
                    null,
                    LocalDate.of(2010,11,7),
                    "Paciente Tarso com dor na face"
            );
        });
    }
    @Test
    void DataNula(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Prontuario(
                    "Arthur",
                    "Ana",
                    "Masculino",
                    "Rua la ele 1000",
                    null,
                    "Paciente Tarso com dor na face"
            );
        });
    }
    @Test
    void DescricaoNula(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Prontuario(
                    "Arthur",
                    "Ana",
                    "Masculino",
                    "Rua la ele 1000",
                    LocalDate.of(2010,11,7),
                    null
            );
        });
    }

}
