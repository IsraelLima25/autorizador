package br.com.desafio.autorizador.usecase.transacao;

import br.com.desafio.autorizador.usecase.command.RealizarTransacaoCommand;
import br.com.desafio.autorizador.usecase.port.output.AtualizarSaldoOutputPort;
import br.com.desafio.autorizador.usecase.port.output.ObterCartaoLockOutputPort;
import br.com.desafio.autorizador.usecase.cartao.CartaoSnapshot;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RealizarTransacaoUseCaseTest {

    @InjectMocks
    private RealizarTransacaoUseCase realizarTransacaoUseCase;

    @Mock
    private ValidadorTransacao validadorTransacao;

    @Mock
    private ObterCartaoLockOutputPort obterCartaoLockOutputPort;

    @Mock
    private AtualizarSaldoOutputPort atualizarSaldoOutputPort;

    @Test
    @DisplayName("Deve realizar transacao com sucesso")
    void deveRealizarTransacao(){
        when(obterCartaoLockOutputPort.obterCartaoLock("1234545454"))
                .thenReturn(new CartaoSnapshot(1L, "1234545454", "123", new BigDecimal("500.00")));
        realizarTransacaoUseCase.execute(new RealizarTransacaoCommand("1234545454", "123", new BigDecimal("100.00")));
        verify(validadorTransacao, times(1)).validar(any());
        verify(atualizarSaldoOutputPort, times(1)).atualizarSaldo(any(), any());
    }
}