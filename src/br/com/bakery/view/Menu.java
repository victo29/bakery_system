package br.com.bakery.view;

import br.com.bakery.erros.EmptyInputException;
import br.com.bakery.model.*;
import br.com.bakery.model.enums.MeioPagamento;
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
            opcao = this.inputInt("Escolha: ", "opção");

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

        int codigo = this.inputInt("Código do Produto: ", "código");
        String descricao = this.inputString("Descrição do produto: ", "descrição do produto");
        double custo = this.inputDouble("Valor de custo: ", "valor de custo");
        double lucro = this.inputDouble("Percentual de lucro: ", "percentual de lucro");
        int estoqueMin = this.inputInt("Estoque mínimo: ", "valor de estoque mínimo");
        int estoqueAtual = this.inputInt("Estoque atual: ", "valor de estoque atual");


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
            System.out.println("0 - Sair");
            opcao = this.inputInt("Escolha: ", "opção");

            switch (opcao) {
                case 1 -> cadastrarVenda();
                case 2 -> buscarVenda();
                case 3 -> gerenciaVendas.RelatorioVendas();
                case 4 -> relatorioPorMes();
                case 5 -> gerenciaVendas.RelatorioVendasPorMeioPagamento(MeioPagamento.FIADO);
                case 6 -> gerenciaVendas.RelatorioVendasPorMeioPagamento(MeioPagamento.DINHEIRO);
                case 0 -> System.out.println("Saindo do menu de vendas...");
                default -> System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    // ===== Métodos auxiliares =====

    private void buscarVenda() {
        int id = this.inputInt("Informe o ID da venda: ", "ID");
        gerenciaVendas.buscarVenda(id);
    }

    private void relatorioPorMes() {
        int mes = this.inputInt("Informe o mês (1 a 12): ", "mês" );
        gerenciaVendas.RelatorioVendasPorMes(mes);
    }

    private void cadastrarVenda() {

        // ===== Cliente =====
        System.out.println("\nTipo de Cliente:");
        System.out.println("1 - Pessoa Física");
        System.out.println("2 - Pessoa Jurídica");
        int tipoCliente = this.inputInt("Tipo do cliente: ", "tipo do cliente");
        String nome =  this.inputString("Nome: ", "nome do cliente");
        String telefone = this.inputString("Telefone: ", "telefone do cliente");

        Cliente cliente;
        if (tipoCliente == 1) {
            String cpf = this.inputString("CPF: ", "CPF do cliente");
            cliente = new ClienteFisico(nome, telefone, cpf);
        } else {
            String cnpj =  this.inputString("CNPJ: ", "CNPJ do cliente");
            String ie = this.inputString("Inscrição Estadual: ", "inscrição estadual");
            cliente = new ClienteJuridico(nome, telefone, cnpj, ie);
        }

        // ===== Produto =====
        this.gerenciaProdutos.listarProdutos();

        int codigo = this.inputInt("Escolha um produto com base no código: ", "código do produto");
        Produto produto = this.gerenciaProdutos.buscarProdutoPorCodigo(codigo);

        // ===== Venda =====
        int quantidade = this.inputInt("Quantidade vendida: ", "quantidade");

        System.out.println(" =================== Meio de pagamento ===================");
        System.out.println("1 - Dinheiro");
        System.out.println("2 - Cheque");
        System.out.println("3 - Cartão Débito");
        System.out.println("4 - Cartão Crédito");
        System.out.println("5 - Ticket Alimentação");
        System.out.println("6 - Fiado");

        int opPagamento = this.inputInt("Opção: ", "opção de pagamento");

        MeioPagamento meioPagamento = switch (opPagamento) {
            case 1 -> MeioPagamento.DINHEIRO;
            case 2 -> MeioPagamento.CHEQUE;
            case 3 -> MeioPagamento.CARTAO_DEBITO;
            case 4 -> MeioPagamento.CARTAO_CREDITO;
            case 5 -> MeioPagamento.TICKET_ALIMENTACAO;
            case 6 -> MeioPagamento.FIADO;
            default -> MeioPagamento.DINHEIRO;
        };

        LocalDate dataAtual = LocalDate.now();
        int dia = dataAtual.getDayOfMonth();
        int mes = dataAtual.getMonthValue();

        Venda venda = new Venda(cliente, dia, mes ,produto, quantidade, meioPagamento);

        gerenciaVendas.cadastrarVenda(venda);
        System.out.println("Venda cadastrada com sucesso!");
    }

    private String inputString(String message, String inputName){
        while (true){
            System.out.println(message);
            try {
                String userResponse = sc.nextLine();

                if(userResponse.isEmpty()){
                    throw new EmptyInputException("Você não pode passar o(a) " + inputName + " vazio!!");
                }

                return userResponse;
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }
    }

    private int inputInt(String message, String inputName) {
        while (true) {
            System.out.println(message);
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Você deve informar um(a) " + inputName + " válido(a)!");
            }
        }
    }

    private double inputDouble(String message, String inputName) {
        while (true) {
            System.out.println(message);
            try {
                return Double.parseDouble(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Você deve informar um(a) " + inputName + " válido(a)!");
            }
        }
    }

}
