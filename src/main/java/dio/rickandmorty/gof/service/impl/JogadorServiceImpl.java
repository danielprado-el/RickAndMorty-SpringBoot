package dio.rickandmorty.gof.service.impl;

import dio.rickandmorty.gof.model.Jogador;
import dio.rickandmorty.gof.model.JogadorRepository;
import dio.rickandmorty.gof.model.Personagem;
import dio.rickandmorty.gof.model.PersonagemRepository;
import dio.rickandmorty.gof.service.JogadorService;
import dio.rickandmorty.gof.service.RickAndMortyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JogadorServiceImpl implements JogadorService {

    // Singleton: Injetar os componentes do Spring com @Autowired.
    @Autowired
    private JogadorRepository jogadorRepository;
    @Autowired
    private PersonagemRepository personagemRepository;
    @Autowired
    private RickAndMortyService rickAndMortyService;

    // Strategy: Implementar os métodos definidos na interface.
    // Facade: Abstrair integrações com subsistemas, provendo uma interface simples.

    @Override
    public Iterable<Jogador> buscarTodos() {
        // Buscar todos os jogadores.
        return jogadorRepository.findAll();
    }

    @Override
    public Jogador buscarPorId(Long id) {
        // Buscar Jogador por ID.
        Optional<Jogador> jogador = jogadorRepository.findById(id);
        return jogador.get();
    }

    @Override
    public void inserir(Jogador jogador) {
        salvarJogadorComPersonagem(jogador);
    }

    @Override
    public void atualizar(Long id, Jogador jogador) {
        // Buscar Jogador por ID, caso exista:
        Optional<Jogador> jogadorBd = jogadorRepository.findById(id);
        if (jogadorBd.isPresent()) {
            salvarJogadorComPersonagem(jogador);
        }
    }

    @Override
    public void deletar(Long id) {
        // Deletar Jogador por ID.
        jogadorRepository.deleteById(id);
    }

    private void salvarJogadorComPersonagem(Jogador jogador) {
        // Verificar se o Personagem do Jogador já existe (pelo NOME).
        String id = jogador.getPersonagem().getId();
        System.out.println(id);
        Personagem personagem = personagemRepository.findById(id).orElseGet(() -> {
            // Caso não exista, integrar com o RickAndMorty e persistir o retorno.
            Personagem novoPersonagem = rickAndMortyService.consultarPersonagem(id);
            System.out.println(novoPersonagem);
            personagemRepository.save(novoPersonagem);
            return novoPersonagem;
        });
        jogador.setPersonagem(personagem);
        // Inserir Jogador, vinculando o Personagem (novo ou existente).
        jogadorRepository.save(jogador);
    }
}
