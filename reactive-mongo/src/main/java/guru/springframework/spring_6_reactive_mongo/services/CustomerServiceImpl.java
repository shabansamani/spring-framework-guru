package guru.springframework.spring_6_reactive_mongo.services;

import org.springframework.stereotype.Service;

import guru.springframework.spring_6_reactive_mongo.mappers.CustomerMapper;
import guru.springframework.spring_6_reactive_mongo.model.CustomerDTO;
import guru.springframework.spring_6_reactive_mongo.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public Flux<CustomerDTO> listCustomers() {
            return customerRepository.findAll()
                    .map(customerMapper::customerToCustomerDTO);
    }

    @Override
    public Mono<CustomerDTO> saveCustomer(Mono<CustomerDTO> customerDTO) {
        return customerDTO.map(customerMapper::customerDTOToCustomer)
                .flatMap(customerRepository::save)
                .map(customerMapper::customerToCustomerDTO);
    }

    @Override
    public Mono<CustomerDTO> saveCustomer(CustomerDTO customerDTO) {
        return saveCustomer(Mono.just(customerDTO));
    }

    @Override
    public Mono<CustomerDTO> getById(String customerId) {
        return customerRepository.findById(customerId)
            .map(customerMapper::customerToCustomerDTO);
    }

    @Override
    public Mono<CustomerDTO> updateCustomer(String customerId, CustomerDTO customerDTO) {
        return customerRepository.findById(customerId)
            .map(foundCustomer -> {
                foundCustomer.setCustomerName(customerDTO.getCustomerName());
                return foundCustomer;
            })
            .flatMap(customerRepository::save)
            .map(customerMapper::customerToCustomerDTO);
    }

    @Override
    public Mono<Void> deleteCustomer(String customerId) {
        return customerRepository.deleteById(customerId);
    }

}
