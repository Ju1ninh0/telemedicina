package org.telemedicina.presentation.cli;

import org.telemedicina.domain.entities.Paciente;
import org.telemedicina.domain.entities.Prontuario;
import org.telemedicina.domain.valueobjects.Cpf;
import org.telemedicina.domain.valueobjects.Email;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("===== CADASTRO DE PACIENTE =====");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Nome da mãe: ");
        String nomeMae = scanner.nextLine();

        System.out.print("Sexo: ");
        String sexo = scanner.nextLine();

        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();

        System.out.print("Ano de nascimento: ");
        int ano = scanner.nextInt();

        System.out.print("Mês de nascimento: ");
        int mes = scanner.nextInt();

        System.out.print("Dia de nascimento: ");
        int dia = scanner.nextInt();

        scanner.nextLine();

        System.out.print("Descrição do paciente: ");
        String descricao = scanner.nextLine();

        System.out.print("CPF: ");
        String cpfDigitado = scanner.nextLine();

        System.out.print("Email: ");
        String emailDigitado = scanner.nextLine();

        Prontuario prontuario = new Prontuario(
                nome,
                nomeMae,
                sexo,
                endereco,
                LocalDate.of(ano, mes, dia),
                descricao
        );

        Cpf cpf = new Cpf(cpfDigitado);
        Email email = new Email(emailDigitado);

        Paciente paciente = new Paciente(
                nome,
                cpf,
                email,
                prontuario
        );

        System.out.println("\n===== PACIENTE CADASTRADO =====");

        System.out.println("Nome: " + paciente.getNome());
        System.out.println("CPF: " + paciente.getCpf().getValor());
        System.out.println("Email: " + paciente.getEmail().getValor());

        scanner.close();
    }
}