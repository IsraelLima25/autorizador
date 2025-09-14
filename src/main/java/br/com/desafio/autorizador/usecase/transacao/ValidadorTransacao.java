package br.com.desafio.autorizador.usecase.transacao;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidadorTransacao {

    private final List<TransacaoValidador> regras;

    public ValidadorTransacao(List<TransacaoValidador> regras) {
        this.regras = regras;
    }

    void validar(ContextoTransacao ctx) {
        for (var regra : regras) regra.validar(ctx);
    }
}
