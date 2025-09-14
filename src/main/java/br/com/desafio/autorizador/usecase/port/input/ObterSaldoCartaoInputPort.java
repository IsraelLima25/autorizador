package br.com.desafio.autorizador.usecase.port.input;

import br.com.desafio.autorizador.adapter.input.dto.SaldoCartaoResponseDTO;

public interface ObterSaldoCartaoInputPort {

    SaldoCartaoResponseDTO execute(String numeroCartao);
}
