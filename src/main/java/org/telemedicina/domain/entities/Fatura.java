package org.telemedicina.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Fatura {

    public enum Status { PENDENTE, PAGA }

    private final String id;
    private final Consulta consulta;
    private final BigDecimal valor;
    private Status status;
    private LocalDateTime dataPagamento;

    public Fatura(String id, Consulta consulta) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID da fatura é obrigatório");
        }
        if (consulta == null) {
            throw new IllegalArgumentException("Consulta é obrigatória");
        }
        if (consulta.getValor().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Valor da fatura não pode ser negativo");
        }
        this.id = id;
        this.consulta = consulta;
        this.valor = consulta.getValor();
        this.status = Status.PENDENTE;
    }

    public void pagar() {
        if (this.status == Status.PAGA) {
            throw new IllegalStateException("Fatura já foi paga — pagamento duplo não permitido");
        }
        this.status = Status.PAGA;
        this.dataPagamento = LocalDateTime.now();
    }

    public String getId() { return id; }
    public Consulta getConsulta() { return consulta; }
    public BigDecimal getValor() { return valor; }
    public Status getStatus() { return status; }
    public LocalDateTime getDataPagamento() { return dataPagamento; }
    public boolean isPaga() { return status == Status.PAGA; }
}
