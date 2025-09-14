package br.com.desafio.autorizador.usecase.port.output;

import br.com.desafio.autorizador.domain.Cartao;

public interface CriarCartaoOutputPort {

    void criar(Cartao cartao);
}
