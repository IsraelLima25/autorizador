package br.com.desafio.autorizador.usecase;

import br.com.desafio.autorizador.adapter.input.dto.SaldoCartaoResponseDTO;
import br.com.desafio.autorizador.domain.exception.CartaoNaoExisteException;
import br.com.desafio.autorizador.usecase.port.input.ObterSaldoCartaoInputPort;
import br.com.desafio.autorizador.usecase.port.output.ExisteCartaoOutputPort;
import br.com.desafio.autorizador.usecase.port.output.ObterSaldoCartaoOutputPort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ObterSaldoCartaoUseCase implements ObterSaldoCartaoInputPort {

    private final ObterSaldoCartaoOutputPort obterSaldoCartaoOutputPort;
    private final ExisteCartaoOutputPort existeCartaoOutputPort;

    public ObterSaldoCartaoUseCase(ObterSaldoCartaoOutputPort obterSaldoCartaoOutputPort,
                                   ExisteCartaoOutputPort existeCartaoOutputPort) {
        this.obterSaldoCartaoOutputPort = obterSaldoCartaoOutputPort;
        this.existeCartaoOutputPort = existeCartaoOutputPort;
    }

    @Override
    public SaldoCartaoResponseDTO execute(String numeroCartao) {
        if(!existeCartaoOutputPort.existeCartao(numeroCartao)){
            throw new CartaoNaoExisteException();
        }
        BigDecimal saldo = obterSaldoCartaoOutputPort.obterSaldo(numeroCartao);
        return new SaldoCartaoResponseDTO(saldo);
    }
}
