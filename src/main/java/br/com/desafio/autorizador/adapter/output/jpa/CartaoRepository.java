package br.com.desafio.autorizador.adapter.output.jpa;

import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;

public interface CartaoRepository extends JpaRepository<CartaoEntity, Long> {

    boolean existsByNumero(String numero);

    @Query("SELECT c.saldo from CartaoEntity c WHERE c.numero = :numero")
    BigDecimal obterSaldo(@Param("numero") String numero);

    @Query("SELECT c.senha from CartaoEntity c WHERE c.numero = :numero")
    String obterSenha(@Param("numero") String numero);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select c from CartaoEntity c where c.numero = :numero")
    @QueryHints(@QueryHint(name = "jakarta.persistence.lock.timeout", value = "3000"))
    Optional<CartaoEntity> obterPorNumeroParaAtualizar(@Param("numero") String numero);

    @Modifying(flushAutomatically = true, clearAutomatically = false)
    @Query("update CartaoEntity c set c.saldo = :novoSaldo where c.id = :id")
    int atualizarSaldoPorId(@Param("id") Long id, @Param("novoSaldo") BigDecimal novoSaldo);
}
