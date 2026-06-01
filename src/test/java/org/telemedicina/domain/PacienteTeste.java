package org.telemedicina.domain;

import org.junit.jupiter.api.Test;
import org.telemedicina.domain.entities.Paciente;
import org.telemedicina.domain.entities.Prontuario;
import org.telemedicina.domain.valueobjects.Cpf;
import org.telemedicina.domain.valueobjects.Email;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class PacienteTeste {


    @Test
    void PacienteValido(){
        Cpf cpf = new Cpf("12345678901");
        Email email = new Email("Teste@gmail.com");
        Prontuario prontuario = new Prontuario("Arthur","Maria","Masculino","Rua sla 1200", LocalDate.of(1970,12,4),"tanto faz");
        Paciente paciente = new Paciente("Arthur",cpf,email,prontuario);
        assertEquals("Arthur", paciente.getNome());
        assertEquals(cpf, paciente.getCpf());
        assertEquals(email, paciente.getEmail());
        assertEquals(prontuario,paciente.getProntuario());
        }
        @Test
        void NomeNulo(){
            Cpf cpf= new Cpf("12345678910");
            Email email = new Email("teste@gmail.com");
            Prontuario prontuario=new Prontuario("Arthur","Maria","Masculino","Rua sla 1200", LocalDate.of(1970,12,4),"tanto faz");
            assertThrows(IllegalArgumentException.class, () -> {
                new Paciente(null, cpf, email,prontuario);
            });
    }
        @Test
        void CpfNulo(){
            Email email= new Email("teste@gmail.com");
            Prontuario prontuario=new Prontuario("Arthur","Maria","Masculino","Rua sla 1200", LocalDate.of(1970,12,4),"tanto faz");
            assertThrows(IllegalArgumentException.class, () -> {
                new Paciente("Arthur", null, email , prontuario);
            });
        }
        @Test
        void EmailNulo(){
            Cpf cpf= new Cpf("12345678910");
            Prontuario prontuario=new Prontuario("Arthur","Maria","Masculino","Rua sla 1200", LocalDate.of(1970,12,4),"tanto faz");
            assertThrows(IllegalArgumentException.class, () -> {
                new Paciente("Arthur", cpf, null , prontuario);
            });
        }
        @Test
    void ProntuarioNulo(){
            Cpf cpf= new Cpf("12345678910");
            Email email = new Email("teste@gmail.com");
            assertThrows(IllegalArgumentException.class, () -> {
                new Paciente("Arthur", cpf, email,null);
            });
        }
}
