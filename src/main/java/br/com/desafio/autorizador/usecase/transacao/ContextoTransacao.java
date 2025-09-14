package br.com.desafio.autorizador.usecase.transacao;

import br.com.desafio.autorizador.usecase.command.RealizarTransacaoCommand;
import br.com.desafio.autorizador.usecase.cartao.CartaoSnapshot;

import java.math.BigDecimal;

public record ContextoTransacao(
        RealizarTransacaoCommand transacaoCommand,
        CartaoSnapshot cartaoSnapshot,
        BigDecimal valor
){}
