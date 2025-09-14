package br.com.desafio.autorizador.usecase.transacao;

import br.com.desafio.autorizador.domain.exception.TransacaoInvalidaException;
import br.com.desafio.autorizador.usecase.command.RealizarTransacaoCommand;
import br.com.desafio.autorizador.usecase.cartao.CartaoSnapshot;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ValidarSaldoCartaoTest {

    @InjectMocks
    private ValidarSaldoCartao validarSaldoCartao;

    @Test
    @DisplayName("Quando uma transação for realizada para um cartao com saldo suficiente deve ser liberada")
    void deveRealizarTransacaoQuandoSaldoSuficiente(){
        validarSaldoCartao.validar(getContextoSaldoPositivo());
    }

    @Test
    @DisplayName("Quando uma transação for realizada para um cartao com saldo insuficiente não deve ser liberada")
    void deveLancarExceptionQuandoSaldoInsuficiente(){
        TransacaoInvalidaException exception = assertThrows(TransacaoInvalidaException.class, () -> {
            validarSaldoCartao.validar(getContextoSaldoNegativo());
        });
        Assertions.assertThat(exception.getMessage()).isEqualTo("SALDO_INSUFICIENTE");
    }

    private ContextoTransacao getContextoSaldoPositivo(){
        return new ContextoTransacao(new RealizarTransacaoCommand("12121212", "123", new BigDecimal("300.00")),
                new CartaoSnapshot(1L, "12121212", "123", new BigDecimal("300.00")), new BigDecimal("100.00"));
    }

    private ContextoTransacao getContextoSaldoNegativo(){
        return new ContextoTransacao(new RealizarTransacaoCommand("12121212", "123", new BigDecimal("100.00")),
                new CartaoSnapshot(1L, "12121212", "123", new BigDecimal("50.00")), new BigDecimal("300.00"));
    }
}