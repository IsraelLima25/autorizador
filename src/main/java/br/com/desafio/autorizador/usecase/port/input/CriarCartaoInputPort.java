package br.com.desafio.autorizador.usecase.port.input;

import br.com.desafio.autorizador.adapter.input.dto.cartao.CriarCartaoResponseDTO;
import br.com.desafio.autorizador.usecase.command.CriarCartaoCommand;

public interface CriarCartaoInputPort {
    CriarCartaoResponseDTO execute(CriarCartaoCommand command);
}