package guru.springframework.spring_6_reactive_r2dbc.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import guru.springframework.spring_6_reactive_r2dbc.domain.Beer;

public interface BeerRepository extends ReactiveCrudRepository<Beer, Integer> {

}
