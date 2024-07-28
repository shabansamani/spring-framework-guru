package guru.springframework.spring_6_reactive_mongo.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import guru.springframework.spring_6_reactive_mongo.domain.Customer;

public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {

}
