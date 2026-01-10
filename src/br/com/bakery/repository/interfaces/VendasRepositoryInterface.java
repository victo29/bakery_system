package br.com.bakery.repository.interfaces;

import br.com.bakery.model.Venda;

import java.util.List;

public interface VendasRepositoryInterface {

    public void salvar(Venda venda);

    public List<Venda> listar();

    public Venda buscarPorId(int id);
}
