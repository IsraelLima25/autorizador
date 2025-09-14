package br.com.desafio.autorizador.usecase.port.output;

import br.com.desafio.autorizador.adapter.output.jpa.CartaoEntity;

public interface CriarCartaoOutputPort {

    CartaoEntity criar(CartaoEntity cartao);
}
