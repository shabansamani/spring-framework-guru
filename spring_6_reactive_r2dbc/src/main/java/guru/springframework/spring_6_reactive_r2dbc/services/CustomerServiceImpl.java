package guru.springframework.spring_6_reactive_r2dbc.services;

import org.springframework.stereotype.Service;

import guru.springframework.spring_6_reactive_r2dbc.mappers.CustomerMapper;
import guru.springframework.spring_6_reactive_r2dbc.model.CustomerDTO;
import guru.springframework.spring_6_reactive_r2dbc.repositories.CustomerRepository;
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
    public Mono<CustomerDTO> getCustomerById(Integer customerId) {
        return customerRepository.findById(customerId)
            .map(customerMapper::customerToCustomerDTO);
    }

    @Override
    public Mono<CustomerDTO> saveNewCustomer(CustomerDTO customerDTO) {
        return customerRepository.save(customerMapper.customerDTOToCustomer(customerDTO))
            .map(customerMapper::customerToCustomerDTO);
    }

    @Override
    public Mono<CustomerDTO> updateCustomer(Integer customerId, CustomerDTO customerDTO) {
        return customerRepository.findById(customerId)
        .map(foundCustomer -> {
            foundCustomer.setCustomerName(customerDTO.getCustomerName());
            return foundCustomer;
        })
        .flatMap(customerRepository::save)
        .map(customerMapper::customerToCustomerDTO);
        
    }

    @Override
    public Mono<CustomerDTO> patchCustomer(Integer customerId, CustomerDTO customerDTO) {
        return customerRepository.findById(customerId)
        .map(foundCustomer -> {
            if(customerDTO.getCustomerName() != null) foundCustomer.setCustomerName(customerDTO.getCustomerName());
            return foundCustomer;
        }).flatMap(customerRepository::save)
        .map(customerMapper::customerToCustomerDTO);
    }

    @Override
    public Mono<Void> deleteCustomer(Integer customerId) {
        return customerRepository.deleteById(customerId);
    }

}
