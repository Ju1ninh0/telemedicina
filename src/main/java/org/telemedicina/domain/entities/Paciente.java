package org.telemedicina.domain.entities;

import org.telemedicina.domain.valueobjects.Cpf;
import org.telemedicina.domain.valueobjects.Email;

public class Paciente {
    private String nome;
    private Cpf cpf;
    private Email email;
    private Prontuario prontuario;

    public Paciente(String nome,Cpf cpf, Email email, Prontuario prontuario){
        if (nome==null || nome.isEmpty()){
            throw new IllegalArgumentException("Nome do Paciente é obrigatório");
        }
        if (cpf==null ){
            throw new IllegalArgumentException("Insira o seu cpf");
        }
        if(email==null){
            throw new IllegalArgumentException("Insira seu email");
        }
        if(prontuario==null){
            throw new IllegalArgumentException("Insira seu prontuário");
        }
        this.nome=nome;
        this.cpf=cpf;
        this.email=email;
        this.prontuario=prontuario;
    }
    public String getNome(){
        return nome;
    }
    public Cpf getCpf(){
        return cpf;
    }
    public Email getEmail(){
        return email;
    }
    public Prontuario getProntuario(){
        return prontuario;
    }
}
