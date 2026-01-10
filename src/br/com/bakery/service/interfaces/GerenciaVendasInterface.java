package br.com.bakery.service.interfaces;

import br.com.bakery.model.Venda;
import br.com.bakery.model.enums.MeioPagamento;

public interface GerenciaVendasInterface {

    void buscarVenda(int id);

    void RelatorioVendas();

    void RelatorioVendasPorMes(int mes);

    void RelatorioVendasPorMeioPagamento(MeioPagamento meioPagamento);

    void RelatorioVendasNaoPagas();

    void cadastrarVenda(Venda venda);

}
