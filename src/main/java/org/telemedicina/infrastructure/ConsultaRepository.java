package org.telemedicina.infrastructure;

import org.telemedicina.domain.entities.Consulta;
import org.telemedicina.domain.entities.Fatura;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ConsultaRepository {

    private final DatabaseConnection db;

    public ConsultaRepository(DatabaseConnection db) {
        this.db = db;
    }

    public String salvarConsulta(Consulta consulta) throws SQLException {
        String id = UUID.randomUUID().toString();
        String sql = "INSERT INTO consultas (id, paciente_id, data_hora, valor) VALUES (?, ?, ?, ?)";
        try (var stmt = db.getConnection().prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.setString(2, consulta.getPaciente().getCpf().getValor());
            stmt.setTimestamp(3, Timestamp.valueOf(consulta.getDataHora()));
            stmt.setBigDecimal(4, consulta.getValor());
            stmt.executeUpdate();
        }
        return id;
    }

    public String salvarFatura(Fatura fatura, String consultaId) throws SQLException {
        String id = UUID.randomUUID().toString();
        String sql = "INSERT INTO faturas (id, consulta_id, valor, status) VALUES (?, ?, ?, ?)";
        try (var stmt = db.getConnection().prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.setString(2, consultaId);
            stmt.setBigDecimal(3, fatura.getValor());
            stmt.setString(4, fatura.getStatus().name());
            stmt.executeUpdate();
        }
        return id;
    }

    public void atualizarPagamentoFatura(String faturaId, Fatura fatura) throws SQLException {
        String sql = "UPDATE faturas SET status = ?, data_pagamento = ? WHERE id = ?";
        try (var stmt = db.getConnection().prepareStatement(sql)) {
            stmt.setString(1, fatura.getStatus().name());
            stmt.setTimestamp(2, fatura.getDataPagamento() != null
                    ? Timestamp.valueOf(fatura.getDataPagamento()) : null);
            stmt.setString(3, faturaId);
            stmt.executeUpdate();
        }
    }

    public Optional<String> buscarStatusFaturaPorConsulta(String consultaId) throws SQLException {
        String sql = "SELECT status FROM faturas WHERE consulta_id = ?";
        try (var stmt = db.getConnection().prepareStatement(sql)) {
            stmt.setString(1, consultaId);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(rs.getString("status"));
            }
        }
        return Optional.empty();
    }

    public List<String[]> listarFaturas() throws SQLException {
        List<String[]> faturas = new ArrayList<>();
        String sql = "SELECT id, consulta_id, valor, status, data_pagamento FROM faturas";
        try (var stmt = db.getConnection().createStatement();
             var rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                faturas.add(new String[]{
                        rs.getString("id"),
                        rs.getString("consulta_id"),
                        rs.getString("valor"),
                        rs.getString("status"),
                        rs.getString("data_pagamento")
                });
            }
        }
        return faturas;
    }
}
