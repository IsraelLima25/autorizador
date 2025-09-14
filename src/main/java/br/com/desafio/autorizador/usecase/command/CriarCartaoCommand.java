package br.com.desafio.autorizador.usecase.command;

public record CriarCartaoCommand(
        String numero,
        String senha
) {}