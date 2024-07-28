package guru.springframework.spring_6_reactive_mongo.services;

import guru.springframework.spring_6_reactive_mongo.model.BeerDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BeerService {
    Flux<BeerDTO> listBeers();
    Mono<BeerDTO> saveBeer(Mono<BeerDTO> beerDTO);
    Mono<BeerDTO> saveBeer(BeerDTO beerDTO);
    Mono<BeerDTO> findFirstByBeerName(String beerName);
    Mono<BeerDTO> getById(String beerId);
    Flux<BeerDTO> findByBeerStyle(String beerStyle);
    Mono<BeerDTO> updateBeer(String beerId, BeerDTO beerDTO);
    Mono<BeerDTO> patchBeer(String beerId, BeerDTO beerDTO);
    Mono<Void> deleteBeer(String beerId);
}
