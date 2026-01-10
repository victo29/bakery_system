package br.com.bakery.view;

import br.com.bakery.model.*;
import br.com.bakery.model.enums.MeioPagamento;
import br.com.bakery.service.GerenciaProdutos;
import br.com.bakery.service.GerenciaVendas;
import br.com.bakery.service.interfaces.GerenciaProdutosInterface;
import br.com.bakery.service.interfaces.GerenciaVendasInterface;

import java.util.Scanner;
import java.time.LocalDate;

public class Menu {

    private final GerenciaVendasInterface gerenciaVendas;
    private final GerenciaProdutosInterface gerenciaProdutos;
    private final Scanner sc = new Scanner(System.in);

    public Menu(GerenciaVendasInterface gerenciaVendas, GerenciaProdutosInterface gerenciaProdutos) {
        this.gerenciaVendas = gerenciaVendas;
        this.gerenciaProdutos = gerenciaProdutos;
    }

    public void exibir() {
        int opcao;

        do {
            System.out.println("\nO que você deseja?");
            System.out.println("1 - Menu de Vendas");
            System.out.println("2 - Cadastrar Produto");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> menuVendas();
                case 2 -> menuProdutos();
                case 0 -> System.out.println("Encerrando sistema...");
                default -> System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    // ================= MENU PRODUTOS =================
    private void menuProdutos() {
        System.out.println("\n===== MENU DE PRODUTOS =====");

        System.out.println("Código do Produto: ");
        int codigo = Integer.parseInt(sc.nextLine());

        System.out.print("Descrição do produto: ");
        String descricao = sc.nextLine();

        System.out.print("Valor de custo: ");
        double custo = Double.parseDouble(sc.nextLine());

        System.out.print("Percentual de lucro: ");
        double lucro = Double.parseDouble(sc.nextLine());

        System.out.print("Estoque mínimo: ");
        int estoqueMin = Integer.parseInt(sc.nextLine());

        System.out.print("Estoque atual: ");
        int estoqueAtual = Integer.parseInt(sc.nextLine());


        Produto produto = new Produto(codigo, descricao, estoqueMin, estoqueAtual , custo, lucro);
        boolean response = gerenciaProdutos.cadastrarProduto(produto);
        if (!response){
            System.out.println("Falha ao cadastrar produto!");
        }

        System.out.println("Produto cadastrado com sucesso!");
    }

    // ================= MENU VENDAS =================
    private void menuVendas() {
        if (!gerenciaProdutos.possuiProdutosCadastrados()) {
            System.out.println("Nenhum produto cadastrado. Cadastre um produto primeiro.");
            return;
        }

        this.exibirMenuVendas();

    }

    private void exibirMenuVendas() {
        int opcao;

        do {

            System.out.println("\n===== MENU DE VENDAS =====");
            System.out.println("1 - Cadastrar Venda");
            System.out.println("2 - Buscar Venda Realizada");
            System.out.println("3 - Relatório de Vendas");
            System.out.println("4 - Relatório de Vendas por Mês");
            System.out.println("5 - Relatório de Vendas (Fiado)");
            System.out.println("6 - Relatório de Vendas (Dinheiro)");
            System.out.println("7 - Relatório de Vendas Não Pagas");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> cadastrarVenda();
                case 2 -> buscarVenda();
                case 3 -> gerenciaVendas.RelatorioVendas();
                case 4 -> relatorioPorMes();
                case 5 -> gerenciaVendas.RelatorioVendasPorMeioPagamento(MeioPagamento.FIADO);
                case 6 -> gerenciaVendas.RelatorioVendasPorMeioPagamento(MeioPagamento.DINHEIRO);
                case 7 -> gerenciaVendas.RelatorioVendasNaoPagas();
                case 0 -> System.out.println("Saindo do menu de vendas...");
                default -> System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    // ===== Métodos auxiliares =====

    private void buscarVenda() {
        System.out.print("Informe o ID da venda: ");
        int id = Integer.parseInt(sc.nextLine());
        gerenciaVendas.buscarVenda(id);
    }

    private void relatorioPorMes() {
        System.out.print("Informe o mês (1 a 12): ");
        int mes = Integer.parseInt(sc.nextLine());
        gerenciaVendas.RelatorioVendasPorMes(mes);
    }

    private void cadastrarVenda() {

        // ===== Cliente =====
        System.out.println("\nTipo de Cliente:");
        System.out.println("1 - Pessoa Física");
        System.out.println("2 - Pessoa Jurídica");
        int tipoCliente = Integer.parseInt(sc.nextLine());


        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        Cliente cliente;
        if (tipoCliente == 1) {
            System.out.print("CPF: ");
            String cpf = sc.nextLine();
            cliente = new ClienteFisico(nome, telefone, cpf);
        } else {
            System.out.print("CNPJ: ");
            String cnpj = sc.nextLine();
            System.out.print("Inscrição Estadual: ");
            String ie = sc.nextLine();
            cliente = new ClienteJuridico(nome, telefone, cnpj, ie);
        }

        // ===== Produto =====
        this.gerenciaProdutos.listarProdutos();

        System.out.println("Escolha um produto com base no código: ");
        int codigo = Integer.parseInt(sc.nextLine());
        Produto produto = this.gerenciaProdutos.buscarProdutoPorCodigo(codigo);

        // ===== Venda =====
        System.out.print("Quantidade vendida: ");
        int quantidade = Integer.parseInt(sc.nextLine());

        System.out.println("Meio de pagamento:");
        System.out.println("1 - Dinheiro");
        System.out.println("2 - Cheque");
        System.out.println("3 - Cartão Débito");
        System.out.println("4 - Cartão Crédito");
        System.out.println("5 - Ticket Alimentação");
        System.out.println("6 - Fiado");

        int opPagamento = sc.nextInt();

        MeioPagamento meioPagamento = switch (opPagamento) {
            case 1 -> MeioPagamento.DINHEIRO;
            case 2 -> MeioPagamento.CHEQUE;
            case 3 -> MeioPagamento.CARTAO_DEBITO;
            case 4 -> MeioPagamento.CARTAO_CREDITO;
            case 5 -> MeioPagamento.TICKET_ALIMENTACAO;
            case 6 -> MeioPagamento.FIADO;
            default -> MeioPagamento.DINHEIRO;
        };

        System.out.print("Pago? (true/false): ");
        boolean pago = sc.nextBoolean();

        LocalDate dataAtual = LocalDate.now();
        int dia = dataAtual.getDayOfMonth();
        int mes = dataAtual.getMonthValue();

        Venda venda = new Venda(cliente, dia, mes ,produto, quantidade, meioPagamento, pago);

        gerenciaVendas.cadastrarVenda(venda);
        System.out.println("Venda cadastrada com sucesso!");
    }
}
