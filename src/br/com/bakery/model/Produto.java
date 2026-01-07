package br.com.bakery.model;

public class Produto {

    private int codigo;
    private String descricao;
    private int estoqueMinimo;
    private int estoqueAtual;
    private double valorCusto;
    private double percentualLucro;
    private double valorVenda;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(int estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }

    public int getEstoqueAtual() {
        return estoqueAtual;
    }

    public void setEstoqueAtual(int estoqueAtual) {
        this.estoqueAtual = estoqueAtual;
    }

    public double getValorCusto() {
        return valorCusto;
    }

    public void setValorCusto(double valorCusto) {
        this.valorCusto = valorCusto;
    }

    public double getPercentualLucro() {
        return percentualLucro;
    }

    public void setPercentualLucro(double percentualLucro) {
        this.percentualLucro = percentualLucro;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public Produto() {

    }

    public Produto(int codigo, String descricao, int estoqueMinimo, int estoqueAtual, double valorCusto, double percentualLucro) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.estoqueMinimo = estoqueMinimo;
        this.estoqueAtual = estoqueAtual;
        this.valorCusto = valorCusto;
        this.percentualLucro = percentualLucro;
        this.valorVenda = valorCusto + (valorCusto * (percentualLucro / 100));
    }
}