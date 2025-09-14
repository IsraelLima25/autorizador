package br.com.desafio.autorizador.usecase.port.output;

import br.com.desafio.autorizador.usecase.cartao.CartaoSnapshot;

public interface ObterCartaoLockOutputPort {

    CartaoSnapshot obterCartaoLock(String numeroCartao);

}
