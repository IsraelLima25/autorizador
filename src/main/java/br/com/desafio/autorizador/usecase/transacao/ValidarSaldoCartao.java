package br.com.desafio.autorizador.usecase.transacao;

import br.com.desafio.autorizador.domain.Cartao;
import br.com.desafio.autorizador.domain.exception.TransacaoInvalidaException;
import org.springframework.stereotype.Component;

@Component
public class ValidarSaldoCartao implements TransacaoValidador{

    @Override
    public void validar(ContextoTransacao ctx) {
        Cartao cartao = new Cartao(ctx.cartaoSnapshot().numero(), ctx.cartaoSnapshot().senha());
        if (cartao.temSaldo(ctx.transacaoCommand().valor(), ctx.cartaoSnapshot().saldo())) {
            throw new TransacaoInvalidaException("SALDO_INSUFICIENTE");
        }
    }
}
