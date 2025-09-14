package br.com.desafio.autorizador.domain;

import java.math.BigDecimal;

public class Cartao {

    private String numero;
    private String senha;
    private BigDecimal saldo = new BigDecimal("500.00");

    public Cartao(String numero, String senha){
        this.numero = numero;
        this.senha = senha;
    }

    public String getNumero() {
        return numero;
    }

    public String getSenha() {
        return senha;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public boolean senhaConfere(String senhaTeste){
        return this.senha.equals(senhaTeste);
    }

    public boolean temSaldo(BigDecimal valorDebito, BigDecimal saldoAtual){
        return saldoAtual.compareTo(valorDebito) < 0;
    }
}