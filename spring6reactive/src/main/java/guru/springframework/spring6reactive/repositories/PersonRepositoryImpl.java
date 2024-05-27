package guru.springframework.spring6reactive.repositories;

import guru.springframework.spring6reactive.domain.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PersonRepositoryImpl implements PersonRepository {

    Person michael = Person.builder().id(1).firstName("Michael").lastName("Weston").build();
    Person fiona = Person.builder().id(2).firstName("Fiona").lastName("Glennane").build();
    Person sam = Person.builder().id(3).firstName("Sam").lastName("Axe").build();
    Person jesse = Person.builder().id(4).firstName("Jesse").lastName("Porter").build();

    @Override
    public Flux<Person> findAll() {
        return Flux.just(michael, fiona, sam, jesse);
    }

    @Override
    public Mono<Person> getById(final Integer id) {
        return findAll().filter(person -> person.getId() == id).next();
    }

}
