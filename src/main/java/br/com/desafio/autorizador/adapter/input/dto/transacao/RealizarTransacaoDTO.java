package br.com.desafio.autorizador.adapter.input.dto.transacao;

import br.com.desafio.autorizador.usecase.command.RealizarTransacaoCommand;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record RealizarTransacaoDTO(
   @NotBlank String numeroCartao,
   @NotBlank String senhaCartao,
   @NotNull @Positive BigDecimal valor
) {

    public RealizarTransacaoCommand toCommand(){
        return new RealizarTransacaoCommand(numeroCartao, senhaCartao, valor);
    }
}
