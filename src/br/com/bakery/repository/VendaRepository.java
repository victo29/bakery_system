package br.com.bakery.repository;

import br.com.bakery.model.Venda;
import br.com.bakery.repository.interfaces.VendasRepositoryInterface;

import java.util.ArrayList;
import java.util.List;

public class VendaRepository implements VendasRepositoryInterface {

    private final List<Venda> vendas;

    public VendaRepository() {
        this.vendas = new ArrayList<>();
    }

    @Override
    public void salvar(Venda venda) {
        vendas.add(venda);
    }

    @Override
    public List<Venda> listar() {
        return vendas;
    }

    @Override
    public Venda buscarPorId(int id) {
        for (Venda venda : vendas) {
            if (venda.getId() == id) {
                return venda;
            }
        }
        return null;
    }

}