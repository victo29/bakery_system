package br.com.bakery.repository;

import br.com.bakery.model.Produto;
import br.com.bakery.repository.interfaces.ProdutoRepositoryInterface;

import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository implements ProdutoRepositoryInterface {

    private final List<Produto> produtos;

    public ProdutoRepository() {
        this.produtos = new ArrayList<>();
    }

    public void salvar(Produto produto) {
        produtos.add(produto);
    }

    public List<Produto> listar() {
        return produtos;
    }

    public Produto buscarPorCodigo(int codigo) {
        for (Produto produto : produtos) {
            if (produto.getCodigo() == codigo) {
                return produto;
            }
        }
        return null;
    }
}
