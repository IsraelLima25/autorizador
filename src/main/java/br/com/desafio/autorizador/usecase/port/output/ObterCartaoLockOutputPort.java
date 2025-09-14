package br.com.desafio.autorizador.usecase.port.output;

import br.com.desafio.autorizador.usecase.snap.CartaoSnapshot;

import java.math.BigDecimal;

public interface ObterCartaoLockOutputPort {

    CartaoSnapshot obterCartaoLock(String numeroCartao);

}
