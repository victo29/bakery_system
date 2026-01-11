package br.com.bakery;

import br.com.bakery.repository.ProdutoRepository;
import br.com.bakery.repository.VendaRepository;
import br.com.bakery.service.GerenciaProdutos;
import br.com.bakery.service.GerenciaVendas;
import br.com.bakery.view.Menu;

public class Main {

    public static void main(String[] args) {

        ProdutoRepository produtoRepository = new ProdutoRepository();
        VendaRepository vendaRepository = new VendaRepository();

        GerenciaProdutos gerenciaProdutos = new GerenciaProdutos(produtoRepository);
        GerenciaVendas gerenciaVendas = new GerenciaVendas(vendaRepository, produtoRepository);

        Menu menu = new Menu(gerenciaVendas, gerenciaProdutos);

        menu.exibir();
    }
}
