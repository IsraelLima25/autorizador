package br.com.desafio.autorizador.usecase.snap;

import java.math.BigDecimal;

public record CartaoSnapshot(
        Long id, String numero, String senha, BigDecimal saldo
) {}
