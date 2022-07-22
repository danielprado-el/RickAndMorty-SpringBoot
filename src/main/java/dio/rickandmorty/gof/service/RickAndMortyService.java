package dio.rickandmorty.gof.service;

import dio.rickandmorty.gof.model.Personagem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "rickandmortyapi", url = "https://rickandmortyapi.com/api")
public interface RickAndMortyService {

    @GetMapping("/character/{id}")
    Personagem consultarPersonagem(@PathVariable("id") String id);

}
