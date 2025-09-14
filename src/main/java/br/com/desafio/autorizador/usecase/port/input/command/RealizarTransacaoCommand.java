package br.com.desafio.autorizador.usecase.port.input.command;

import java.math.BigDecimal;

public record RealizarTransacaoCommand(
        String numeroCartao,
        String senhaCartao,
        BigDecimal valor
){}
