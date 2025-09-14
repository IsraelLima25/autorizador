package br.com.desafio.autorizador.adapter.output.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface CartaoRepository extends JpaRepository<CartaoEntity, Long> {

    boolean existsByNumero(String numero);

    @Query("SELECT c.saldo from CartaoEntity c WHERE c.numero = :numero")
    BigDecimal obterSaldo(@Param("numero") String numero);
}
