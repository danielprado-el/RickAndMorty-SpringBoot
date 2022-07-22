package dio.rickandmorty.gof.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonagemRepository extends CrudRepository<Personagem, String> {
}
