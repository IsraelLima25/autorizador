package br.com.desafio.autorizador.usecase;

import br.com.desafio.autorizador.domain.exception.CartaoRepetidoException;
import br.com.desafio.autorizador.usecase.cartao.CriarCartaoUseCase;
import br.com.desafio.autorizador.usecase.command.CriarCartaoCommand;
import br.com.desafio.autorizador.usecase.port.output.CriarCartaoOutputPort;
import br.com.desafio.autorizador.usecase.port.output.ExisteCartaoOutputPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CriarCartaoUseCaseTest {

    @InjectMocks
    private CriarCartaoUseCase criarCartaoUseCase;

    @Mock
    private ExisteCartaoOutputPort existeCartaoOutputPort;

    @Mock
    private CriarCartaoOutputPort criarCartaoOutputPort;

    @Test
    @DisplayName("Deve criar cartão")
    void deveCriarCartao(){
        CriarCartaoCommand cartaoCommand = new CriarCartaoCommand("152121215454", "123456");
        criarCartaoUseCase.execute(cartaoCommand);
        verify(criarCartaoOutputPort, times(1)).criar(any());
    }

    @Test
    @DisplayName("Quando criar um cartão se ele já existir deve lançar uma exception")
    void quandoCartaoRepetidoDeveLancarException(){
        CriarCartaoCommand cartaoCommand = new CriarCartaoCommand("152121215454", "123456");
        when(existeCartaoOutputPort.existeCartao(cartaoCommand.numero())).thenThrow(new CartaoRepetidoException(cartaoCommand.numero(),
                cartaoCommand.senha()));

        CartaoRepetidoException exception = assertThrows(CartaoRepetidoException.class, () -> {
            criarCartaoUseCase.execute(cartaoCommand);
        });
        verify(criarCartaoOutputPort, never()).criar(any());
        assertThat(exception.getNumero()).isEqualTo(cartaoCommand.numero());
        assertThat(exception.getSenha()).isEqualTo(cartaoCommand.senha());

    }
}