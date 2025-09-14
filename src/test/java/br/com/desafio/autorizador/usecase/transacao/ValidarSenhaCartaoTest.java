package br.com.desafio.autorizador.usecase.transacao;

import br.com.desafio.autorizador.domain.exception.TransacaoInvalidaException;
import br.com.desafio.autorizador.usecase.command.RealizarTransacaoCommand;
import br.com.desafio.autorizador.usecase.port.output.ObterSenhaCartaoOutputPort;
import br.com.desafio.autorizador.usecase.cartao.CartaoSnapshot;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ValidarSenhaCartaoTest {

    @InjectMocks
    private ValidarSenhaCartao validarSenhaCartao;

    @Mock
    private ObterSenhaCartaoOutputPort obterSenhaCartaoOutputPort;

    @Test
    @DisplayName("Quando uma transacao for realizada com a senha correta do cartao deve ser liberada")
    void deveRealizarTransacaoQuandoSenhaValida(){
        when(obterSenhaCartaoOutputPort.obterSenha(any())).thenReturn("123");
        validarSenhaCartao.validar(getContextoSenhaCorreta());
    }

    @Test
    @DisplayName("Quando uma transacao for realizada com a senha incorreta do cartao deve ser bloqueada")
    void deveLancarExceptionQuandoTransacaoSenhaInvalida(){
        when(obterSenhaCartaoOutputPort.obterSenha(any())).thenReturn("123");
        TransacaoInvalidaException exception = assertThrows(TransacaoInvalidaException.class, () -> {
            validarSenhaCartao.validar(getContextoSenhaIncorreta());
        });
        assertThat(exception.getMessage()).isEqualTo("SENHA_INVALIDA");
    }

    private ContextoTransacao getContextoSenhaCorreta(){
        return new ContextoTransacao(new RealizarTransacaoCommand("12121212", "123", new BigDecimal("300.00")),
                new CartaoSnapshot(1L, "12121212", "123", new BigDecimal("300.00")), new BigDecimal("100.00"));
    }

    private ContextoTransacao getContextoSenhaIncorreta(){
        return new ContextoTransacao(new RealizarTransacaoCommand("12121212", "1234", new BigDecimal("100.00")),
                new CartaoSnapshot(1L, "12121212", "123", new BigDecimal("50.00")), new BigDecimal("300.00"));
    }
}