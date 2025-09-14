package br.com.desafio.autorizador.usecase.cartao;

import br.com.desafio.autorizador.adapter.input.dto.cartao.CriarCartaoResponseDTO;
import br.com.desafio.autorizador.domain.Cartao;
import br.com.desafio.autorizador.domain.exception.CartaoRepetidoException;
import br.com.desafio.autorizador.usecase.port.input.CriarCartaoInputPort;
import br.com.desafio.autorizador.usecase.command.CriarCartaoCommand;
import br.com.desafio.autorizador.usecase.port.output.CriarCartaoOutputPort;
import br.com.desafio.autorizador.usecase.port.output.ExisteCartaoOutputPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CriarCartaoUseCase implements CriarCartaoInputPort {

    private final CriarCartaoOutputPort criarCartaoOutputPort;
    private final ExisteCartaoOutputPort existeCartaoOutputPort;

    public CriarCartaoUseCase(CriarCartaoOutputPort criarCartaoOutputPort,
                              ExisteCartaoOutputPort existeCartaoOutputPort) {
        this.criarCartaoOutputPort = criarCartaoOutputPort;
        this.existeCartaoOutputPort = existeCartaoOutputPort;
    }

    @Transactional
    @Override
    public CriarCartaoResponseDTO execute(CriarCartaoCommand command) {
        Cartao cartao = new Cartao(command.numero(), command.senha());
        if(existeCartao(cartao.getNumero())){
            throw new CartaoRepetidoException(cartao.getNumero(), cartao.getSenha());
        }
        criarCartaoOutputPort.criar(cartao);
        return new CriarCartaoResponseDTO(cartao.getNumero(), cartao.getSenha());
    }

    private boolean existeCartao(String numeroCartao){
        return existeCartaoOutputPort.existeCartao(numeroCartao);
    }
}