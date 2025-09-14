package br.com.desafio.autorizador.domain.exception;

public class CartaoNaoExisteException extends RuntimeException{

    public CartaoNaoExisteException(){}

    public CartaoNaoExisteException(String mensagem){
        super(mensagem);
    }
}
