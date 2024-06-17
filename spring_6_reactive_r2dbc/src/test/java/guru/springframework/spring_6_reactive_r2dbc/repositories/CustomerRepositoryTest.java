package guru.springframework.spring_6_reactive_r2dbc.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import guru.springframework.spring_6_reactive_r2dbc.config.DatabaseConfig;
import guru.springframework.spring_6_reactive_r2dbc.domain.Customer;

@DataR2dbcTest
@Import(DatabaseConfig.class)
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void testCreateJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(getTestCustomer()));
    }

    @Test
    void testSaveCustomer() {
        customerRepository.save(getTestCustomer())
            .subscribe(customer -> {
                System.out.println(customer.toString());
            });
    }

    public static Customer getTestCustomer() {
        return Customer.builder().customerName("Johnny Lawrence").build();
    }

}
