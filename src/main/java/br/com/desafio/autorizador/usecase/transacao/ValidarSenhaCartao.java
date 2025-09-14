package br.com.desafio.autorizador.usecase.transacao;

import br.com.desafio.autorizador.domain.Cartao;
import br.com.desafio.autorizador.domain.exception.TransacaoInvalidaException;
import br.com.desafio.autorizador.usecase.port.output.ObterSenhaCartaoOutputPort;
import org.springframework.stereotype.Component;

@Component
public class ValidarSenhaCartao implements TransacaoValidador {

    private final ObterSenhaCartaoOutputPort obterSenhaCartaoOutputPort;

    public ValidarSenhaCartao(ObterSenhaCartaoOutputPort obterSenhaCartaoOutputPort) {
        this.obterSenhaCartaoOutputPort = obterSenhaCartaoOutputPort;
    }

    @Override
    public void validar(ContextoTransacao ctx) {
        String senhaVerdadeira = obterSenhaCartaoOutputPort.obterSenha(ctx.transacaoCommand().numeroCartao());
        Cartao cartao = new Cartao(ctx.transacaoCommand().numeroCartao(), ctx.transacaoCommand().senhaCartao());
        if(!cartao.senhaConfere(senhaVerdadeira)){
            throw new TransacaoInvalidaException("SENHA_INVALIDA");
        }
    }
}
