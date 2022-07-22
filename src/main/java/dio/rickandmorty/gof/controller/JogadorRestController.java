package dio.rickandmorty.gof.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dio.rickandmorty.gof.model.Jogador;
import dio.rickandmorty.gof.service.JogadorService;

/**
 * Esse {@link RestController} representa a <b>Facade</b>, pois abstrai toda
 * a complexidade de integrações (Banco de Dados H2 e API do Rick And Morty) em uma
 * interface simples e coesa (API REST).
 *
 */
@RestController
@RequestMapping("jogadores")
public class JogadorRestController {

    @Autowired
    private JogadorService jogadorService;

    @GetMapping
    public ResponseEntity<Iterable<Jogador>> buscarTodos() {
        return ResponseEntity.ok(jogadorService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jogador> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(jogadorService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Jogador> inserir(@RequestBody Jogador jogador) {
        jogadorService.inserir(jogador);
        return ResponseEntity.ok(jogador);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jogador> atualizar(@PathVariable Long id, @RequestBody Jogador jogador) {
        jogadorService.atualizar(id, jogador);
        return ResponseEntity.ok(jogador);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        jogadorService.deletar(id);
        return ResponseEntity.ok().build();
    }

}
