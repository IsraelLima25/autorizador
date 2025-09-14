package br.com.desafio.autorizador.adapter.exception;

import br.com.desafio.autorizador.domain.exception.CartaoNaoExisteException;
import br.com.desafio.autorizador.domain.exception.CartaoRepetidoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(CartaoRepetidoException.class)
    public ResponseEntity<Map<String, String>> handlerCartaoRepetido(CartaoRepetidoException ex) {
        var body = Map.of(
                "senha", ex.getSenha(),
                "numero", ex.getNumero()
        );
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(body);
    }

    @ExceptionHandler(CartaoNaoExisteException.class)
    public ResponseEntity<Map<String, String>> handlerCartaoNaoExiste(CartaoNaoExisteException ex) {
        return ResponseEntity.notFound().build();
    }
}
