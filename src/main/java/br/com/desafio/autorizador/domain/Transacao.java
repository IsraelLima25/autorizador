package br.com.desafio.autorizador.domain;

import java.math.BigDecimal;

public class Transacao {

    private Cartao cartao;
    private BigDecimal valor;

    public Transacao(Cartao cartao, BigDecimal valor) {
        this.cartao = cartao;
        this.valor = valor;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
