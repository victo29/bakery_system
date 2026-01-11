package br.com.bakery.repository;

import br.com.bakery.erros.ProductNotFoundException;
import br.com.bakery.model.Produto;
import br.com.bakery.repository.interfaces.ProdutoRepositoryInterface;

import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository implements ProdutoRepositoryInterface {

    private final List<Produto> produtos;

    public ProdutoRepository() {
        this.produtos = new ArrayList<>();
    }

    @Override
    public void salvar(Produto produto) {
        produtos.add(produto);
    }

    @Override
    public List<Produto> listar() {
        return produtos;
    }

    @Override
    public Produto buscarPorCodigo(int codigo) {
        for (Produto produto : produtos) {
            if (produto.getCodigo() == codigo) {
                return produto;
            }
        }
        return null;
    }

    @Override
    public void atualizarProduto(Produto produto) {
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getCodigo() == produto.getCodigo()) {
                produtos.set(i, produto);
                return;
            }
        }

        throw new ProductNotFoundException("Produto não encontrado para atualização.");
    }
}
