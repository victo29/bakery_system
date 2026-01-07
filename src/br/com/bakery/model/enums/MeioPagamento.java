package br.com.bakery.model.enums;

public enum MeioPagamento {

    DINHEIRO("Dinheiro"),
    CHEQUE("Cheque"),
    CARTAO_DEBITO("Cartão de Débito"),
    CARTAO_CREDITO("Cartão de Crédito"),
    TICKET_ALIMENTACAO("Ticket Alimentação"),
    FIADO("Fiado");

    private final String descricao;

    MeioPagamento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}