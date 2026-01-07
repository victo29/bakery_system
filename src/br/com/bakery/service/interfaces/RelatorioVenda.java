package br.com.bakery.service.interfaces;

import br.com.bakery.model.enums.MeioPagamento;

public interface RelatorioVenda {

    void buscarVenda(int id);

    void RelatorioVendas();

    void RelatorioVendasPorMes(int mes);

    void RelatorioVendasPorMeioPagamento(MeioPagamento meioPagamento);

    void RelatorioVendasNaoPagas();
}
