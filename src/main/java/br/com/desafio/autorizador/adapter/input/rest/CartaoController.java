package br.com.desafio.autorizador.adapter.input.rest;

import br.com.desafio.autorizador.adapter.input.dto.CriarCartaoDTO;
import br.com.desafio.autorizador.adapter.input.dto.CriarCartaoResponseDTO;
import br.com.desafio.autorizador.usecase.port.input.CriarCartaoInputPort;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {

    private final CriarCartaoInputPort criarCartaoInputPort;

    public CartaoController(CriarCartaoInputPort criarCartaoInputPort) {
        this.criarCartaoInputPort = criarCartaoInputPort;
    }

    @PostMapping
    public ResponseEntity<CriarCartaoResponseDTO> criar(@Valid @RequestBody CriarCartaoDTO criarCartaoDTO,
                                                        UriComponentsBuilder uriBuilder){
        var cartaoCriado = criarCartaoInputPort.execute(criarCartaoDTO.toCommand());
        URI uri = uriBuilder.path("/cartoes/{numeroCartao}").buildAndExpand(cartaoCriado.numero()).toUri();
        return ResponseEntity.created(uri).body(cartaoCriado);
    }
}
