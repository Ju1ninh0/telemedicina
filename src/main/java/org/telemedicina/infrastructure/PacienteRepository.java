package org.telemedicina.infrastructure;

import org.telemedicina.domain.entities.Paciente;
import org.telemedicina.domain.entities.Prontuario;
import org.telemedicina.domain.valueobjects.Cpf;
import org.telemedicina.domain.valueobjects.Email;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PacienteRepository {

    private final DatabaseConnection db;

    public PacienteRepository(DatabaseConnection db) {
        this.db = db;
    }

    public String salvar(Paciente paciente) throws SQLException {
        String id = UUID.randomUUID().toString();
        String sql = "INSERT INTO pacientes (id, nome, cpf, email) VALUES (?, ?, ?, ?)";
        try (var stmt = db.getConnection().prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.setString(2, paciente.getNome());
            stmt.setString(3, paciente.getCpf().getValor());
            stmt.setString(4, paciente.getEmail().getValor());
            stmt.executeUpdate();
        }
        return id;
    }

    public Optional<Paciente> buscarPorCpf(String cpf) throws SQLException {
        String sql = "SELECT nome, cpf, email FROM pacientes WHERE cpf = ?";
        try (var stmt = db.getConnection().prepareStatement(sql)) {
            stmt.setString(1, cpf);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                Prontuario prontuarioPlaceholder = new Prontuario(
                        rs.getString("nome"), "N/A", "N/A", "N/A",
                        LocalDate.now(), "Carregado do banco"
                );
                Paciente paciente = new Paciente(
                        rs.getString("nome"),
                        new Cpf(rs.getString("cpf")),
                        new Email(rs.getString("email")),
                        prontuarioPlaceholder
                );
                return Optional.of(paciente);
            }
        }
        return Optional.empty();
    }

    public List<Paciente> listarTodos() throws SQLException {
        List<Paciente> pacientes = new ArrayList<>();
        String sql = "SELECT nome, cpf, email FROM pacientes";
        try (var stmt = db.getConnection().createStatement();
             var rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Prontuario prontuarioPlaceholder = new Prontuario(
                        rs.getString("nome"), "N/A", "N/A", "N/A",
                        LocalDate.now(), "Carregado do banco"
                );
                pacientes.add(new Paciente(
                        rs.getString("nome"),
                        new Cpf(rs.getString("cpf")),
                        new Email(rs.getString("email")),
                        prontuarioPlaceholder
                ));
            }
        }
        return pacientes;
    }
}
