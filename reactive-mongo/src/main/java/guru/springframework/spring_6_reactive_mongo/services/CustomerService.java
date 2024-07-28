package guru.springframework.spring_6_reactive_mongo.services;

import guru.springframework.spring_6_reactive_mongo.model.CustomerDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {
    Flux<CustomerDTO> listCustomers();
    Mono<CustomerDTO> saveCustomer(Mono<CustomerDTO> customerDTO);
    Mono<CustomerDTO> saveCustomer(CustomerDTO customerDTO);
    Mono<CustomerDTO> getById(String customerId);
    Mono<CustomerDTO> updateCustomer(String customerId, CustomerDTO customerDTO);
    Mono<Void> deleteCustomer(String customerId);
}
