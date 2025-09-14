package br.com.desafio.autorizador.usecase.port.input.command;

public record CriarCartaoCommand(
        String numero,
        String senha
) {}