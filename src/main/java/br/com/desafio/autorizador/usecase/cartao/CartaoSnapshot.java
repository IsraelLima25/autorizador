package br.com.desafio.autorizador.usecase.cartao;

import java.math.BigDecimal;

public record CartaoSnapshot(
        Long id, String numero, String senha, BigDecimal saldo
) {}
