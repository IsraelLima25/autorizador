package br.com.desafio.autorizador.usecase.transacao;

import br.com.desafio.autorizador.usecase.port.input.command.RealizarTransacaoCommand;
import br.com.desafio.autorizador.usecase.snap.CartaoSnapshot;

import java.math.BigDecimal;

public record ContextoTransacao(
        RealizarTransacaoCommand transacaoCommand,
        CartaoSnapshot cartaoSnapshot,
        BigDecimal valor
){}
