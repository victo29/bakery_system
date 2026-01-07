package br.com.bakery.service;

import br.com.bakery.model.Produto;
import br.com.bakery.repository.ProdutoRepository;

import java.util.List;

public class GerenciaProdutos {

    private final ProdutoRepository produtoRepository;

    public GerenciaProdutos(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public boolean cadastrarProduto(Produto produto) {

        Produto produtoEncontrado = produtoRepository.buscarPorCodigo(produto.getCodigo());

        if(produtoEncontrado != null){
            System.out.println("Já existe um produto com o código: " + produto.getCodigo() );
            return false;
        }

        produtoRepository.salvar(produto);
        return true;
    }

    public void listarProdutos() {
        List<Produto> produtos =  produtoRepository.listar();

        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        produtos.forEach(this::imprimirProduto);

    }

    public Produto buscarProdutoPorCodigo(int codigo) {
        Produto produto = produtoRepository.buscarPorCodigo(codigo);

        if(produto == null) {
           System.out.println("Produto do código " + codigo + " não foi encontrado");
           return null;
        }

        return produto;
    }

    public boolean possuiProdutosCadastrados() {
        return !produtoRepository.listar().isEmpty();
    }

    private void imprimirProduto(Produto produto){
        System.out.println("====================================");
        System.out.println("Código do produto : " + produto.getCodigo());
        System.out.println("Descrição" + produto.getDescricao());
        System.out.println("Percentual de lucro: " + produto.getPercentualLucro());
        System.out.println("Estoque atual: " + produto.getEstoqueAtual());
        System.out.println("Valor de custo: " + produto.getValorCusto());
        System.out.println("Valor de venda: " + produto.getValorVenda());
    }
}
