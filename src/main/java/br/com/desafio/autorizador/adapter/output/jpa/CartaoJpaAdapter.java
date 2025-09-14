package br.com.desafio.autorizador.adapter.output.jpa;

import br.com.desafio.autorizador.domain.Cartao;
import br.com.desafio.autorizador.domain.exception.TransacaoInvalidaException;
import br.com.desafio.autorizador.usecase.port.output.*;
import br.com.desafio.autorizador.usecase.cartao.CartaoSnapshot;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Component
public class CartaoJpaAdapter implements CriarCartaoOutputPort, ExisteCartaoOutputPort,
        ObterSaldoCartaoOutputPort, ObterSenhaCartaoOutputPort, ObterCartaoLockOutputPort, AtualizarSaldoOutputPort {

    private final CartaoRepository cartaoRepository;

    public CartaoJpaAdapter(CartaoRepository cartaoRepository) {
        this.cartaoRepository = cartaoRepository;
    }

    @Override
    public void criar(Cartao cartao) {
        var cartaoEntity = new CartaoEntity(cartao.getNumero(), cartao.getSenha(), cartao.getSaldo());
        cartaoRepository.save(cartaoEntity);
    }

    @Override
    public boolean existeCartao(String numero) {
        return cartaoRepository.existsByNumero(numero);
    }

    @Override
    public BigDecimal obterSaldo(String numero){
        return cartaoRepository.obterSaldo(numero);
    }

    @Override
    public String obterSenha(String numeroCartao) {
        return cartaoRepository.obterSenha(numeroCartao);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void atualizarSaldo(Long id, BigDecimal novoSaldo) {
        int n = cartaoRepository.atualizarSaldoPorId(id, novoSaldo);
        if (n == 0) throw new TransacaoInvalidaException("CARTAO_INEXISTENTE");
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public CartaoSnapshot obterCartaoLock(String numeroCartao) {
        var e = cartaoRepository.obterPorNumeroParaAtualizar(numeroCartao)
                .orElseThrow(() -> new TransacaoInvalidaException("CARTAO_INEXISTENTE"));
        return new CartaoSnapshot(e.getId(), e.getNumero(), e.getSenha(), e.getSaldo());
    }
}