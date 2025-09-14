package br.com.desafio.autorizador.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

class CartaoTest {

    private static final BigDecimal SALDO_FIXO_CARTAO = new BigDecimal("500.00");

    @Test
    @DisplayName("Quando o cartão for criado o saldo deve ser igual a 500")
    void quandoCartaoCriadoSaldoDeveSerIgual500(){
        Cartao cartao = new Cartao("6549873025634501", "123456");
        assertThat(cartao.getSaldo()).isEqualTo(SALDO_FIXO_CARTAO);
    }

    @Test
    @DisplayName("Quando a senha errada for informada a validação deve rejeitar o valor")
    void quandoSenhaErradaDeveRetornarFalse(){
        Cartao cartao = new Cartao("12454552254", "1232");
        boolean senhaValida = cartao.senhaConfere("1233");
        assertThat(senhaValida).isFalse();
    }

    @Test
    @DisplayName("Quando a senha correta for informada a validação não deve rejeitar o valor")
    void quandoSenhaCorretaDeveRetornarTrue(){
        Cartao cartao = new Cartao("12454552254", "1232");
        boolean senhaValida = cartao.senhaConfere("1232");
        assertThat(senhaValida).isTrue();
    }
}