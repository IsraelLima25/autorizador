package br.com.desafio.autorizador.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

class CartaoTest {

    private static final BigDecimal SALDO_FIXO_CARTAO = new BigDecimal("500.00");

    @Test
    void quandoCartaoCriadoSaldoDeveSerIgual500(){
        Cartao cartao = new Cartao("6549873025634501", "123456");
        assertThat(cartao.getSaldo()).isEqualTo(SALDO_FIXO_CARTAO);
    }
}