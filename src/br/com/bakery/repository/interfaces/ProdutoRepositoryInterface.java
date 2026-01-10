package br.com.bakery.repository.interfaces;

import br.com.bakery.model.Produto;

import java.util.List;

public interface ProdutoRepositoryInterface {



    public void salvar(Produto produto);

    public List<Produto> listar();

    public Produto buscarPorCodigo(int codigo);

}
