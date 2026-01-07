package br.com.bakery.model;

public class ClienteFisico extends Cliente {

    private String cpf;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


    public ClienteFisico( String nome, String telefone, String cpf) {
        super(nome, telefone);
        this.cpf = cpf;
    }
}
