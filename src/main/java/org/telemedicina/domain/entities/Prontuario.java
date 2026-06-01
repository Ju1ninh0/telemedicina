package org.telemedicina.domain.entities;

import java.time.LocalDate;

public class Prontuario {
    private final String nome;
    private final String nomeMae;
    private final String sexo;
    private final String endereco;
    private final LocalDate dataNascimento;
    private final String descricao;

    public Prontuario(String nome, String nomeMae, String sexo, String endereco, LocalDate dataNascimento, String descricao){
        if(nome==null || nome.isEmpty()){
            throw new IllegalArgumentException("Nome inválido");
        }

        if(nomeMae==null || nomeMae.isEmpty()){
            throw new IllegalArgumentException("Nome da mãe inválido");
        }

        if(sexo==null || sexo.isEmpty()){
            throw new IllegalArgumentException("Sexo inválido");
        }

        if(endereco==null || endereco.isEmpty()){
            throw new IllegalArgumentException("Endereço inválido");
        }

        if(dataNascimento==null ){
            throw new IllegalArgumentException("Data de nascimento inválida");
        }
        if(descricao==null || descricao.isEmpty()){
            throw new IllegalArgumentException("Descricao não pode ser vazia");
            //na descricao é bom ter 2 campos de escrever um para o medico deixar os possiveis
            //diagnosticos ou o so o diagnostico final, alem da descricao que o paciente deu para ele doq ta sentindo
        }

        this.nome=nome;
        this.nomeMae=nomeMae;
        this.sexo=sexo;
        this.endereco=endereco;
        this.dataNascimento=dataNascimento;
        this.descricao=descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public String getSexo() {
        return sexo;
    }

    public String getEndereco() {
        return endereco;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getDescricao() {
        return descricao;
    }
}
