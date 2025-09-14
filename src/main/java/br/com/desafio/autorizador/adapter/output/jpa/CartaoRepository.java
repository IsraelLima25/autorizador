package br.com.desafio.autorizador.adapter.output.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoRepository extends JpaRepository<CartaoEntity, Long> {

    boolean existsByNumero(String numero);
}
