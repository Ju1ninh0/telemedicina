package org.telemedicina.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:h2:mem:telemedicina;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    private static DatabaseConnection instancia;
    private final Connection connection;

    private DatabaseConnection() throws SQLException {
        this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        inicializarEsquema();
    }

    public static DatabaseConnection getInstancia() throws SQLException {
        if (instancia == null || instancia.connection.isClosed()) {
            instancia = new DatabaseConnection();
        }
        return instancia;
    }

    public Connection getConnection() {
        return connection;
    }

    private void inicializarEsquema() throws SQLException {
        try (var stmt = connection.createStatement()) {
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS pacientes (
                    id          VARCHAR(36)  PRIMARY KEY,
                    nome        VARCHAR(255) NOT NULL,
                    cpf         VARCHAR(11)  NOT NULL UNIQUE,
                    email       VARCHAR(255) NOT NULL
                )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS consultas (
                    id          VARCHAR(36)    PRIMARY KEY,
                    paciente_id VARCHAR(36)    NOT NULL,
                    data_hora   TIMESTAMP      NOT NULL,
                    valor       DECIMAL(10, 2) NOT NULL,
                    FOREIGN KEY (paciente_id) REFERENCES pacientes(id)
                )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS faturas (
                    id              VARCHAR(36)    PRIMARY KEY,
                    consulta_id     VARCHAR(36)    NOT NULL UNIQUE,
                    valor           DECIMAL(10, 2) NOT NULL,
                    status          VARCHAR(20)    NOT NULL DEFAULT 'PENDENTE',
                    data_pagamento  TIMESTAMP,
                    FOREIGN KEY (consulta_id) REFERENCES consultas(id)
                )
            """);
        }
    }
}
