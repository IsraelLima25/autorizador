package br.com.desafio.autorizador.usecase.port.output;

import java.math.BigDecimal;

public interface AtualizarSaldoOutputPort {

    void atualizarSaldo(Long id, BigDecimal novoSaldo);
}
