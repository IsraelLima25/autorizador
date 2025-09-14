package br.com.desafio.autorizador.usecase.port.input;

import br.com.desafio.autorizador.adapter.input.dto.CriarCartaoResponseDTO;
import br.com.desafio.autorizador.usecase.port.input.command.CriarCartaoCommand;

public interface CriarCartaoInputPort {
    CriarCartaoResponseDTO execute(CriarCartaoCommand command);
}