package br.com.bakery.model;

public abstract class Cliente {

    private static int count = 0;

    protected int id;
    protected String nome;
    protected String telefone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Cliente() {
        this.id = count++;
    }

    public Cliente(String nome, String telefone) {
        this.id = count++;
        this.nome = nome;
        this.telefone = telefone;
    }





}
