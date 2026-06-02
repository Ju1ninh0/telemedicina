package org.telemedicina.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.telemedicina.domain.entities.*;
import org.telemedicina.domain.valueobjects.Cpf;
import org.telemedicina.domain.valueobjects.Email;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class FaturaTeste {

    private Paciente paciente;
    private Medico medico;
    private Consulta consulta;

    @BeforeEach
    void setUp() {
        Prontuario prontuario = new Prontuario(
                "João Silva", "Maria Silva", "Masculino",
                "Rua das Flores, 10", LocalDate.of(1990, 5, 20),
                "Paciente com dores de cabeça frequentes"
        );
        paciente = new Paciente("João Silva",
                new Cpf("12345678901"),
                new Email("joao@email.com"),
                prontuario);
        Medico medico = new Medico(
                1L,
                "Dr. João",
                "CRM12345",
                "Cardiologia",
                "82999999999",
                "joao@hospital.com"
        );
        consulta = new Consulta("C-001", paciente, medico,
                LocalDateTime.now(), new BigDecimal("150.00"));
    }

    @Test
    @DisplayName("Deve criar fatura com status PENDENTE")
    void deveCriarFaturaComStatusPendente() {
        Fatura fatura = new Fatura("F-001", consulta);

        assertEquals(Fatura.Status.PENDENTE, fatura.getStatus());
        assertFalse(fatura.isPaga());
    }

    @Test
    @DisplayName("Deve registrar pagamento corretamente")
    void devePagarFatura() {
        Fatura fatura = new Fatura("F-001", consulta);

        fatura.pagar();

        assertEquals(Fatura.Status.PAGA, fatura.getStatus());
        assertTrue(fatura.isPaga());
        assertNotNull(fatura.getDataPagamento());
    }

    @Test
    @DisplayName("Não deve permitir pagamento duplo")
    void naoDevePermitirPagamentoDuplo() {
        Fatura fatura = new Fatura("F-001", consulta);
        fatura.pagar();

        IllegalStateException ex = assertThrows(
                IllegalStateException.class, fatura::pagar
        );
        assertTrue(ex.getMessage().contains("já foi paga"));
    }

    @Test
    @DisplayName("Não deve permitir valor negativo na consulta")
    void naoDevePermitirValorNegativo() {
        assertThrows(IllegalArgumentException.class, () ->
                new Consulta("C-002", paciente, medico,
                        LocalDateTime.now(), new BigDecimal("-50.00"))
        );
    }

    @Test
    @DisplayName("Deve herdar valor da consulta")
    void deveHerdarValorDaConsulta() {
        Fatura fatura = new Fatura("F-001", consulta);

        assertEquals(new BigDecimal("150.00"), fatura.getValor());
    }

    @Test
    @DisplayName("Deve rejeitar fatura sem ID")
    void deveRejeitarFaturaSemId() {
        assertThrows(IllegalArgumentException.class, () ->
                new Fatura("", consulta)
        );
    }

    @Test
    @DisplayName("Deve rejeitar fatura sem consulta")
    void deveRejeitarFaturaSemConsulta() {
        assertThrows(IllegalArgumentException.class, () ->
                new Fatura("F-001", null)
        );
    }
}
