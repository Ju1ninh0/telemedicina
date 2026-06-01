package org.telemedicina.domain;

import org.junit.jupiter.api.Test;
import org.telemedicina.domain.valueobjects.Cpf;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CpfTeste {

    @Test
    void CpfValido() {
        Cpf cpf = new Cpf("12345678910");
        assertEquals("12345678910", cpf.getValor());
    }

    @Test
    void CpfNulo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Cpf(null);
        });
    }

    @Test
    void CpfSemTamanho() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Cpf("123");
        });
    }

    @Test
    void CpfComLetra() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Cpf("12345abc901");
        });
    }
}