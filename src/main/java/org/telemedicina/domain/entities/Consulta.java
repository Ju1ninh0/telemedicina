package org.telemedicina.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Consulta {

    private final String id;
    private final Paciente paciente;
    private final Medico medico;
    private final LocalDateTime dataHora;
    private final BigDecimal valor;

    public Consulta(String id, Paciente paciente, Medico medico, LocalDateTime dataHora, BigDecimal valor) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID da consulta é obrigatório");
        }
        if (paciente == null) {
            throw new IllegalArgumentException("Paciente é obrigatório");
        }
        if (medico == null) {
            throw new IllegalArgumentException("Médico é obrigatório");
        }
        if (dataHora == null) {
            throw new IllegalArgumentException("Data e hora são obrigatórios");
        }
        if (valor == null || valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Valor da consulta não pode ser negativo");
        }
        this.id = id;
        this.paciente = paciente;
        this.medico = medico;
        this.dataHora = dataHora;
        this.valor = valor;
    }

    public String getId() { return id; }
    public Paciente getPaciente() { return paciente; }
    public Medico getMedico() { return medico; }
    public LocalDateTime getDataHora() { return dataHora; }
    public BigDecimal getValor() { return valor; }
}