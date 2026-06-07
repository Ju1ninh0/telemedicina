package org.telemedicina.presentation.cli;

import org.telemedicina.domain.entities.*;
import org.telemedicina.domain.valueobjects.Cpf;
import org.telemedicina.domain.valueobjects.Email;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static final List<Paciente> pacientes = new ArrayList<>();
    private static final List<Medico> medicos = new ArrayList<>();
    private static final List<Consulta> consultas = new ArrayList<>();
    private static final List<Fatura> faturas = new ArrayList<>();

    public static void main(String[] args) {

        while (true) {

            try {
                System.out.println("\n==============================");
                System.out.println("      SISTEMA TELEMEDICINA");
                System.out.println("==============================");
                System.out.println("1 - Cadastrar Paciente");
                System.out.println("2 - Listar Pacientes");
                System.out.println("3 - Cadastrar Médico");
                System.out.println("4 - Listar Médicos");
                System.out.println("5 - Agendar Consulta");
                System.out.println("6 - Listar Consultas");
                System.out.println("7 - Gerar Fatura");
                System.out.println("8 - Listar Faturas");
                System.out.println("9 - Pagar Fatura");
                System.out.println("0 - Sair");
                System.out.print("Opção: ");

                int opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1 -> cadastrarPaciente();
                    case 2 -> listarPacientes();
                    case 3 -> cadastrarMedico();
                    case 4 -> listarMedicos();
                    case 5 -> agendarConsulta();
                    case 6 -> listarConsultas();
                    case 7 -> gerarFatura();
                    case 8 -> listarFaturas();
                    case 9 -> pagarFatura();
                    case 0 -> {
                        System.out.println("Sistema encerrado.");
                        return;
                    }
                    default -> System.out.println("Opção inválida.");
                }

            } catch (Exception e) {
                System.out.println("Erro: entrada inválida.");
            }
        }
    }

    private static void cadastrarPaciente() {

        System.out.println("\n===== CADASTRO DE PACIENTE =====");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Nome da mãe: ");
        String nomeMae = scanner.nextLine();

        System.out.print("Sexo: ");
        String sexo = scanner.nextLine();

        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();

        System.out.print("Ano nascimento: ");
        int ano = Integer.parseInt(scanner.nextLine());

        System.out.print("Mês nascimento: ");
        int mes = Integer.parseInt(scanner.nextLine());

        System.out.print("Dia nascimento: ");
        int dia = Integer.parseInt(scanner.nextLine());

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        System.out.print("CPF: ");
        String cpfTexto = scanner.nextLine();

        System.out.print("Email: ");
        String emailTexto = scanner.nextLine();

        Prontuario prontuario = new Prontuario(
                nome,
                nomeMae,
                sexo,
                endereco,
                LocalDate.of(ano, mes, dia),
                descricao
        );

        Paciente paciente = new Paciente(
                nome,
                new Cpf(cpfTexto),
                new Email(emailTexto),
                prontuario
        );

        pacientes.add(paciente);

        System.out.println("Paciente cadastrado com sucesso!");
    }

    private static void listarPacientes() {

        System.out.println("\n===== PACIENTES =====");

        if (pacientes.isEmpty()) {
            System.out.println("Nenhum paciente cadastrado.");
            return;
        }

        for (int i = 0; i < pacientes.size(); i++) {
            Paciente p = pacientes.get(i);
            System.out.println((i + 1) + " - " + p.getNome() + " | CPF: " + p.getCpf().getValor());
        }
    }

    private static void cadastrarMedico() {

        System.out.println("\n===== CADASTRO DE MÉDICO =====");

        System.out.print("ID: ");
        Long id = Long.parseLong(scanner.nextLine());

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("CRM: ");
        String crm = scanner.nextLine();

        System.out.print("Especialidade: ");
        String especialidade = scanner.nextLine();

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        Medico medico = new Medico(id, nome, crm, especialidade, telefone, email);
        medicos.add(medico);

        System.out.println("Médico cadastrado com sucesso!");
    }

    private static void listarMedicos() {

        System.out.println("\n===== MÉDICOS =====");

        if (medicos.isEmpty()) {
            System.out.println("Nenhum médico cadastrado.");
            return;
        }

        for (int i = 0; i < medicos.size(); i++) {
            Medico m = medicos.get(i);
            System.out.println((i + 1) + " - " + m.getNome() + " | CRM: " + m.getCrm());
        }
    }

    private static void agendarConsulta() {

        if (pacientes.isEmpty() || medicos.isEmpty()) {
            System.out.println("Cadastre pacientes e médicos primeiro.");
            return;
        }

        listarPacientes();
        System.out.print("Paciente: ");
        int p = Integer.parseInt(scanner.nextLine()) - 1;

        listarMedicos();
        System.out.print("Médico: ");
        int m = Integer.parseInt(scanner.nextLine()) - 1;

        if (p < 0 || p >= pacientes.size() || m < 0 || m >= medicos.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        System.out.print("ID consulta: ");
        String id = scanner.nextLine();

        System.out.print("Valor: ");
        BigDecimal valor = new BigDecimal(scanner.nextLine());

        consultas.add(new Consulta(
                id,
                pacientes.get(p),
                medicos.get(m),
                LocalDateTime.now(),
                valor
        ));

        System.out.println("Consulta agendada!");
    }

    private static void listarConsultas() {

        System.out.println("\n===== CONSULTAS =====");

        if (consultas.isEmpty()) {
            System.out.println("Nenhuma consulta cadastrada.");
            return;
        }

        for (Consulta c : consultas) {
            System.out.println(
                    "ID: " + c.getId() +
                            " | Paciente: " + c.getPaciente().getNome() +
                            " | Médico: " + c.getMedico().getNome() +
                            " | Valor: " + c.getValor()
            );
        }
    }

    private static void gerarFatura() {

        if (consultas.isEmpty()) {
            System.out.println("Nenhuma consulta cadastrada.");
            return;
        }

        listarConsultas();

        System.out.print("Consulta: ");
        int i = Integer.parseInt(scanner.nextLine()) - 1;

        if (i < 0 || i >= consultas.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        System.out.print("ID fatura: ");
        String id = scanner.nextLine();

        faturas.add(new Fatura(id, consultas.get(i)));

        System.out.println("Fatura criada!");
    }

    private static void listarFaturas() {

        System.out.println("\n===== FATURAS =====");

        if (faturas.isEmpty()) {
            System.out.println("Nenhuma fatura cadastrada.");
            return;
        }

        for (int i = 0; i < faturas.size(); i++) {
            Fatura f = faturas.get(i);
            System.out.println((i + 1) + " | ID: " + f.getId() + " | Valor: " + f.getValor());
        }
    }

    private static void pagarFatura() {

        if (faturas.isEmpty()) {
            System.out.println("Nenhuma fatura cadastrada.");
            return;
        }

        listarFaturas();

        System.out.print("Fatura: ");
        int i = Integer.parseInt(scanner.nextLine()) - 1;

        if (i < 0 || i >= faturas.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        faturas.get(i).pagar();

        System.out.println("Fatura paga!");
    }
}