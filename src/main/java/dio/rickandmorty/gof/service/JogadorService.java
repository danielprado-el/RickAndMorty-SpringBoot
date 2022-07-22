package dio.rickandmorty.gof.service;

import dio.rickandmorty.gof.model.Jogador;

/**
 * Interface que define o padrão <b>Strategy</b> no domínio de jogador. Com
 * isso, se necessário, podemos ter multiplas implementações dessa mesma
 * interface.
 *
 */
public interface JogadorService {

    Iterable<Jogador> buscarTodos();

    Jogador buscarPorId(Long id);

    void inserir(Jogador jogador);

    void atualizar(Long id, Jogador jogador);

    void deletar(Long id);
}
