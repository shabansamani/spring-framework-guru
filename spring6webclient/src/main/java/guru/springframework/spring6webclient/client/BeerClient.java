package guru.springframework.spring6webclient.client;

import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

import guru.springframework.spring6webclient.model.BeerDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BeerClient {
    Flux<String> listBeer();

    Flux<Map> listBeerMap();

    Flux<JsonNode> listBeerJsonNode();

    Flux<BeerDTO> listBeerDtos();

	Mono<BeerDTO> getBeerById(String id);

	Flux<BeerDTO> getBeerByBeerStyle(String beerStyle);

	Mono<BeerDTO> createBeer(BeerDTO newDto);

    Mono<BeerDTO> updateBeer(BeerDTO dto);

	Mono<Void> deleteBeer(BeerDTO beerDTO);

    Mono<BeerDTO> patchBeer(BeerDTO beerDTO);
}
