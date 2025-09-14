package br.com.desafio.autorizador.adapter.input.dto;

import br.com.desafio.autorizador.usecase.port.input.command.CriarCartaoCommand;
import jakarta.validation.constraints.NotBlank;

public record CriarCartaoDTO(

        @NotBlank
        String numero,
        @NotBlank
        String senha
) {

    public CriarCartaoCommand toCommand(){
        return new CriarCartaoCommand(numero, senha);
    }
}