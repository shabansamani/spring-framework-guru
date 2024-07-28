package guru.springframework.spring_6_reactive_mongo.mappers;

import org.mapstruct.Mapper;

import guru.springframework.spring_6_reactive_mongo.domain.Customer;
import guru.springframework.spring_6_reactive_mongo.model.CustomerDTO;

@Mapper
public interface CustomerMapper {
    CustomerDTO customerToCustomerDTO(Customer customer);
    Customer customerDTOToCustomer(CustomerDTO customerDTO);
}
