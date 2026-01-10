package br.com.bakery.service;

import br.com.bakery.model.ClienteFisico;
import br.com.bakery.model.ClienteJuridico;
import br.com.bakery.model.Venda;
import br.com.bakery.model.enums.MeioPagamento;
import br.com.bakery.repository.VendaRepository;
import br.com.bakery.service.interfaces.GerenciaVendasInterface;

import java.util.List;

public class GerenciaVendas implements GerenciaVendasInterface {

    private final VendaRepository vendaRepository;

    public GerenciaVendas(VendaRepository vendaRepository) {
        this.vendaRepository = vendaRepository;
    }

    public void cadastrarVenda(Venda venda) {
        vendaRepository.salvar(venda);
    }

    @Override
    public void buscarVenda(int id) {
        Venda venda = vendaRepository.buscarPorId(id);

        if (venda == null) {
            System.out.println("Venda não encontrada.");
            return;
        }

        imprimirVenda(venda);
    }

    @Override
    public void RelatorioVendas() {
        List<Venda> vendas = vendaRepository.listar();

        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda cadastrada.");
            return;
        }

        vendas.forEach(this::imprimirVenda);
    }

    @Override
    public void RelatorioVendasPorMes(int mes) {
        vendaRepository.listar().stream()
                .filter(v -> v.getMesVenda() == mes)
                .forEach(this::imprimirVenda);
    }

    @Override
    public void RelatorioVendasPorMeioPagamento(MeioPagamento meioPagamento) {
        vendaRepository.listar().stream()
                .filter(v -> v.getMeioPagamento() == meioPagamento)
                .forEach(this::imprimirVenda);
    }

    @Override
    public void RelatorioVendasNaoPagas() {
        vendaRepository.listar().stream()
                .filter(v -> !v.statusVenda())
                .forEach(this::imprimirVenda);
    }

    private void imprimirVenda(Venda venda) {
        System.out.println("====================================");
        System.out.println("ID da Venda: " + venda.getId());
        System.out.println("Data: " + venda.getDiaVenda() + "/" + venda.getMesVenda());

        System.out.println("\nCliente:");
        System.out.println("Nome: " + venda.getCliente().getNome());
        System.out.println("Telefone: " + venda.getCliente().getTelefone());

        if (venda.getCliente() instanceof ClienteFisico cf) {
            System.out.println("CPF: " + cf.getCpf());
        }
        else if (venda.getCliente() instanceof ClienteJuridico cj) {
            System.out.println("CNPJ: " + cj.getCnpj());
            System.out.println("Inscrição Estadual: " + cj.getInscricaoEstadual());
        }

        System.out.println("\nProduto:");
        System.out.println("Descrição: " + venda.getProduto().getDescricao());
        System.out.println("Valor unitário: R$ " + venda.getProduto().getValorVenda());
        System.out.println("Percentual de lucro: " + venda.getProduto().getPercentualLucro() + "%");
        System.out.println("Código do produto: " + venda.getProduto().getCodigo() );

        System.out.println("\nVenda:");
        System.out.println("Quantidade: " + venda.getQuantidadeVendida());
        System.out.println("Meio de pagamento: " + venda.getMeioPagamento().getDescricao());
        System.out.println("Status: " + (venda.statusVenda() ? "Pago" : "Não pago"));
        System.out.println("Valor total: R$ " + venda.getValorVenda());
        System.out.println("====================================");
    }
}
