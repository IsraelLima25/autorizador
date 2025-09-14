package br.com.desafio.autorizador.adapter.output.jpa;

import br.com.desafio.autorizador.usecase.port.output.CriarCartaoOutputPort;
import br.com.desafio.autorizador.usecase.port.output.ExisteCartaoOutputPort;
import org.springframework.stereotype.Component;

@Component
public class CartaoJpaAdapter implements CriarCartaoOutputPort, ExisteCartaoOutputPort {

    private final CartaoRepository cartaoRepository;

    public CartaoJpaAdapter(CartaoRepository cartaoRepository) {
        this.cartaoRepository = cartaoRepository;
    }

    @Override
    public CartaoEntity criar(CartaoEntity cartao) {
        return cartaoRepository.save(cartao);
    }

    @Override
    public boolean existeCartao(String numero) {
        return cartaoRepository.existsByNumero(numero);
    }
}