package br.com.desafio.autorizador.usecase.port.output;

import java.math.BigDecimal;

public interface ObterSaldoCartaoOutputPort {

    BigDecimal obterSaldo(String numeroCartao);
}
