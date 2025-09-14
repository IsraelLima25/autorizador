package br.com.desafio.autorizador.domain.exception;

public class CartaoRepetidoException extends RuntimeException {

    private final String numero;
    private final String senha;

    public CartaoRepetidoException(String numero, String senha) {
        super("Este cartão já existe");
        this.numero = numero;
        this.senha = senha;
    }

    public String getNumero() {
        return numero;
    }

    public String getSenha() {
        return senha;
    }
}
