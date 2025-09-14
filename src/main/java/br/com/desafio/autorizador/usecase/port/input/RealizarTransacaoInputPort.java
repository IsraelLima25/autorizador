package br.com.desafio.autorizador.usecase.port.input;

import br.com.desafio.autorizador.usecase.command.RealizarTransacaoCommand;

public interface RealizarTransacaoInputPort {

    void execute(RealizarTransacaoCommand realizarTransacaoCommand);
}
