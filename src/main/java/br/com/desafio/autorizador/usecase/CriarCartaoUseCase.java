package br.com.desafio.autorizador.usecase;

import br.com.desafio.autorizador.adapter.input.dto.CriarCartaoResponseDTO;
import br.com.desafio.autorizador.adapter.output.jpa.CartaoEntity;
import br.com.desafio.autorizador.domain.Cartao;
import br.com.desafio.autorizador.domain.exception.CartaoRepetidoException;
import br.com.desafio.autorizador.usecase.port.input.CriarCartaoInputPort;
import br.com.desafio.autorizador.usecase.port.input.command.CriarCartaoCommand;
import br.com.desafio.autorizador.usecase.port.output.CriarCartaoOutputPort;
import br.com.desafio.autorizador.usecase.port.output.ExisteCartaoOutputPort;
import org.springframework.stereotype.Service;

@Service
public class CriarCartaoUseCase implements CriarCartaoInputPort {

    private final CriarCartaoOutputPort criarCartaoOutputPort;
    private final ExisteCartaoOutputPort existeCartaoOutputPort;

    public CriarCartaoUseCase(CriarCartaoOutputPort criarCartaoOutputPort,
                              ExisteCartaoOutputPort existeCartaoOutputPort) {
        this.criarCartaoOutputPort = criarCartaoOutputPort;
        this.existeCartaoOutputPort = existeCartaoOutputPort;
    }

    @Override
    public CriarCartaoResponseDTO execute(CriarCartaoCommand command) {
        Cartao cartao = new Cartao(command.numero(), command.senha());
        if(existeCartao(cartao.getNumero())){
            throw new CartaoRepetidoException(cartao.getNumero(), cartao.getSenha());
        }
        var cartaoEntity = new CartaoEntity(cartao.getNumero(), cartao.getSenha(), cartao.getSaldo());
        criarCartaoOutputPort.criar(cartaoEntity);
        return new CriarCartaoResponseDTO(cartao.getNumero(), cartao.getSenha());
    }

    private boolean existeCartao(String numeroCartao){
        return existeCartaoOutputPort.existeCartao(numeroCartao);
    }
}