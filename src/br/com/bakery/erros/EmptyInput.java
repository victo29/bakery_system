package br.com.bakery.erros;

public class EmptyInput extends RuntimeException {
    public EmptyInput(String message) {
        super(message);
    }
}
