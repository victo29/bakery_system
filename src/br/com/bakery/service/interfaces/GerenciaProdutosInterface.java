package br.com.bakery.service.interfaces;

import br.com.bakery.model.Produto;
import br.com.bakery.model.Venda;

public interface GerenciaProdutosInterface {

    public boolean cadastrarProduto(Produto produto);

    public void listarProdutos();

    public Produto buscarProdutoPorCodigo(int codigo);

    public boolean possuiProdutosCadastrados();

}
