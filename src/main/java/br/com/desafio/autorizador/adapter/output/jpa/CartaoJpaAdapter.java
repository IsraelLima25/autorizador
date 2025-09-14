package br.com.desafio.autorizador.adapter.output.jpa;

import br.com.desafio.autorizador.usecase.port.output.CriarCartaoOutputPort;
import br.com.desafio.autorizador.usecase.port.output.ExisteCartaoOutputPort;
import br.com.desafio.autorizador.usecase.port.output.ObterSaldoCartaoOutputPort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Component
public class CartaoJpaAdapter implements CriarCartaoOutputPort, ExisteCartaoOutputPort, ObterSaldoCartaoOutputPort {

    private final CartaoRepository cartaoRepository;

    public CartaoJpaAdapter(CartaoRepository cartaoRepository) {
        this.cartaoRepository = cartaoRepository;
    }

    @Transactional
    @Override
    public CartaoEntity criar(CartaoEntity cartao) {
        return cartaoRepository.save(cartao);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existeCartao(String numero) {
        return cartaoRepository.existsByNumero(numero);
    }

    @Transactional(readOnly = true)
    @Override
    public BigDecimal obterSaldo(String numero){
        return cartaoRepository.obterSaldo(numero);
    }
}