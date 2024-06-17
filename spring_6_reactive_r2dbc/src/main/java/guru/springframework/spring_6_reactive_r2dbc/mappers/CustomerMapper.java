package guru.springframework.spring_6_reactive_r2dbc.mappers;

import org.mapstruct.Mapper;

import guru.springframework.spring_6_reactive_r2dbc.domain.Customer;
import guru.springframework.spring_6_reactive_r2dbc.model.CustomerDTO;

@Mapper
public interface CustomerMapper {
    Customer customerDTOToCustomer(CustomerDTO customerDTO);
    CustomerDTO customerToCustomerDTO(Customer customer);
    
}
