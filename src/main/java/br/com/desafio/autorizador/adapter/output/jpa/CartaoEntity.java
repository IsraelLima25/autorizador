package br.com.desafio.autorizador.adapter.output.jpa;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tbl_cartao")
public class CartaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "numero", unique = true)
    private String numero;

    @Column(nullable = false, name = "senha")
    private String senha;

    @Column(nullable = false, name = "saldo")
    private BigDecimal saldo;

    public CartaoEntity(String numero, String senha, BigDecimal saldo) {
        this.numero = numero;
        this.senha = senha;
        this.saldo = saldo;
    }

    public Long getId() {
        return id;
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
}
