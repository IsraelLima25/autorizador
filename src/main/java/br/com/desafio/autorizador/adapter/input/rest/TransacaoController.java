package br.com.desafio.autorizador.adapter.input.rest;

import br.com.desafio.autorizador.adapter.input.dto.RealizarTransacaoDTO;
import br.com.desafio.autorizador.usecase.port.input.RealizarTransacaoInputPort;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    private final RealizarTransacaoInputPort realizarTransacaoInputPort;

    public TransacaoController(RealizarTransacaoInputPort realizarTransacaoInputPort) {
        this.realizarTransacaoInputPort = realizarTransacaoInputPort;
    }

    @PostMapping
    public ResponseEntity<String> realizar(@Valid @RequestBody RealizarTransacaoDTO realizarTransacaoDTO){
        realizarTransacaoInputPort.execute(realizarTransacaoDTO.toCommand());
        return ResponseEntity.ok("OK");
    }
}
