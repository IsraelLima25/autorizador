package br.com.desafio.autorizador.usecase.transacao;

import br.com.desafio.autorizador.usecase.port.input.RealizarTransacaoInputPort;
import br.com.desafio.autorizador.usecase.command.RealizarTransacaoCommand;
import br.com.desafio.autorizador.usecase.port.output.AtualizarSaldoOutputPort;
import br.com.desafio.autorizador.usecase.port.output.ObterCartaoLockOutputPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RealizarTransacaoUseCase implements RealizarTransacaoInputPort {

    private final ValidadorTransacao validadorTransacao;
    private final ObterCartaoLockOutputPort obterCartaoLockOutputPort;
    private final AtualizarSaldoOutputPort atualizarSaldoOutputPort;

    public RealizarTransacaoUseCase(ValidadorTransacao validadorTransacao, ObterCartaoLockOutputPort obterCartaoLockOutputPort,
                                    AtualizarSaldoOutputPort atualizarSaldoOutputPort) {
        this.validadorTransacao = validadorTransacao;
        this.obterCartaoLockOutputPort = obterCartaoLockOutputPort;
        this.atualizarSaldoOutputPort = atualizarSaldoOutputPort;
    }

    @Transactional
    @Override
    public void execute(RealizarTransacaoCommand transacaoCommand) {
        var cartaoSnap = obterCartaoLockOutputPort.obterCartaoLock(transacaoCommand.numeroCartao());
        validadorTransacao.validar(new ContextoTransacao(transacaoCommand, cartaoSnap, transacaoCommand.valor()));
        var novoSaldo = cartaoSnap.saldo().subtract(transacaoCommand.valor());
        atualizarSaldoOutputPort.atualizarSaldo(cartaoSnap.id(), novoSaldo);
    }
}
