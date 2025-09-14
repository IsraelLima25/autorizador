package br.com.desafio.autorizador.domain.exception;

public class TransacaoInvalidaException extends RuntimeException{

    public TransacaoInvalidaException(String mensagem){
        super(mensagem);
    }
}
