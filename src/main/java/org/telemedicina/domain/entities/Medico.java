package org.telemedicina.domain.entities;

public class Medico {

    private final Long id;
    private final String nome;
    private final String crm;
    private final String especialidade;
    private final String telefone;
    private final String email;

    public Medico(Long id,
                  String nome,
                  String crm,
                  String especialidade,
                  String telefone,
                  String email) {

        if (id == null) {
            throw new IllegalArgumentException("ID é obrigatório");
        }

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }

        if (crm == null || crm.isBlank()) {
            throw new IllegalArgumentException("CRM é obrigatório");
        }

        if (especialidade == null || especialidade.isBlank()) {
            throw new IllegalArgumentException("Especialidade é obrigatória");
        }

        this.id = id;
        this.nome = nome;
        this.crm = crm;
        this.especialidade = especialidade;
        this.telefone = telefone;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCrm() {
        return crm;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return """
                Medico{
                    id=%d,
                    nome='%s',
                    crm='%s',
                    especialidade='%s',
                    telefone='%s',
                    email='%s'
                }
                """.formatted(
                id,
                nome,
                crm,
                especialidade,
                telefone,
                email
        );
    }
}