package br.com.bakery.model;

import br.com.bakery.model.enums.MeioPagamento;

public class Venda {
    private static int count = 0;

    private int id;
    private Cliente cliente;
    private Produto produto;
    private int diaVenda;
    private int mesVenda;
    private int quantidadeVendida;
    private double valorVenda;
    private MeioPagamento meioPagamento;
    private boolean statusVenda;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getDiaVenda() {
        return diaVenda;
    }

    public void setDiaVenda(int diaVenda) {
        this.diaVenda = diaVenda;
    }

    public int getMesVenda() {
        return mesVenda;
    }

    public void setMesVenda(int mesVenda) {
        this.mesVenda = mesVenda;
    }

    public int getQuantidadeVendida() {
        return quantidadeVendida;
    }

    public void setQuantidadeVendida(int quantidadeVendida) {
        this.quantidadeVendida = quantidadeVendida;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public MeioPagamento getMeioPagamento() {
        return meioPagamento;
    }

    public void setMeioPagamento(MeioPagamento meioPagamento) {
        this.meioPagamento = meioPagamento;
    }

    public boolean statusVenda() {
        return statusVenda;
    }

    public void setStatusVenda(boolean statusVenda) {
        this.statusVenda = statusVenda;
    }

    public Venda() {
        this.id = count++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Venda(Cliente cliente, int dia, int mes, Produto produto, int qtd, MeioPagamento meio, boolean status) {
        this.id = count++;
        this.cliente = cliente;
        this.diaVenda = dia;
        this.mesVenda = mes;
        this.produto = produto;
        this.quantidadeVendida = qtd;
        this.meioPagamento = meio;
        this.statusVenda = status;
        this.valorVenda = produto.getValorVenda() * qtd;
    }
}
