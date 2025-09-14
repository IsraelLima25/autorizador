package br.com.desafio.autorizador.adapter.input.rest;

import br.com.desafio.autorizador.adapter.input.dto.CriarCartaoDTO;
import br.com.desafio.autorizador.adapter.input.dto.CriarCartaoResponseDTO;
import br.com.desafio.autorizador.adapter.input.dto.SaldoCartaoResponseDTO;
import br.com.desafio.autorizador.usecase.port.input.CriarCartaoInputPort;
import br.com.desafio.autorizador.usecase.port.input.ObterSaldoCartaoInputPort;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {

    private final CriarCartaoInputPort criarCartaoInputPort;
    private final ObterSaldoCartaoInputPort obterSaldoCartaoInputPort;

    public CartaoController(CriarCartaoInputPort criarCartaoInputPort,
                            ObterSaldoCartaoInputPort obterSaldoCartaoInputPort) {
        this.criarCartaoInputPort = criarCartaoInputPort;
        this.obterSaldoCartaoInputPort = obterSaldoCartaoInputPort;
    }

    @PostMapping
    public ResponseEntity<CriarCartaoResponseDTO> criar(@Valid @RequestBody CriarCartaoDTO criarCartaoDTO,
                                                        UriComponentsBuilder uriBuilder){
        var cartaoCriado = criarCartaoInputPort.execute(criarCartaoDTO.toCommand());
        URI uri = uriBuilder.path("/cartoes/{numeroCartao}").buildAndExpand(cartaoCriado.numero()).toUri();
        return ResponseEntity.created(uri).body(cartaoCriado);
    }

    @GetMapping("/{numeroCartao}")
    public ResponseEntity<SaldoCartaoResponseDTO> obterSaldo(@PathVariable("numeroCartao") String numeroCartao){

        var saldoCartao = obterSaldoCartaoInputPort.execute(numeroCartao);
        return ResponseEntity.ok(saldoCartao);
    }
}
