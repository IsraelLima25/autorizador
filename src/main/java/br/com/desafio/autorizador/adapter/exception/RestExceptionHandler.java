package br.com.desafio.autorizador.adapter.exception;

import br.com.desafio.autorizador.domain.exception.CartaoNaoExisteException;
import br.com.desafio.autorizador.domain.exception.CartaoRepetidoException;
import br.com.desafio.autorizador.domain.exception.TransacaoInvalidaException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler {

    private MessageSource messageSource;

    public RestExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

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

    @ExceptionHandler(TransacaoInvalidaException.class)
    public ResponseEntity<String> handlerTransacaoInvalida(TransacaoInvalidaException ex) {
        return ResponseEntity.unprocessableEntity().body(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public List<CampoInvalido> handlerArgumentNotValid(MethodArgumentNotValidException exception) {

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        List<CampoInvalido> camposInvalidos = extrairErros(fieldErrors);
        return camposInvalidos;
    }

    private List<CampoInvalido> extrairErros(List<FieldError> fieldErrors){
        List<CampoInvalido> camposInvalido = new ArrayList<>();
        fieldErrors.forEach(error -> {
            CampoInvalido fieldErro = new CampoInvalido(error.getField(),
                    messageSource.getMessage(error, LocaleContextHolder.getLocale()));
            camposInvalido.add(fieldErro);
        });
        return camposInvalido;
    }
}
