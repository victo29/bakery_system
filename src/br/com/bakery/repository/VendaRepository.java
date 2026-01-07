package br.com.bakery.repository;

import br.com.bakery.model.Venda;

import java.util.ArrayList;
import java.util.List;

public class VendaRepository {

    private final List<Venda> vendas;

    public VendaRepository() {
        this.vendas = new ArrayList<>();
    }

    public void salvar(Venda venda) {
        vendas.add(venda);
    }

    public List<Venda> listar() {
        return vendas;
    }

    public Venda buscarPorId(int id) {
        for (Venda venda : vendas) {
            if (venda.getId() == id) {
                return venda;
            }
        }
        return null;
    }

}