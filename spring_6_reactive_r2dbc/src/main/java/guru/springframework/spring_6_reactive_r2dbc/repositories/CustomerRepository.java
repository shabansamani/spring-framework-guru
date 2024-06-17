package guru.springframework.spring_6_reactive_r2dbc.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import guru.springframework.spring_6_reactive_r2dbc.domain.Customer;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer>{

}
