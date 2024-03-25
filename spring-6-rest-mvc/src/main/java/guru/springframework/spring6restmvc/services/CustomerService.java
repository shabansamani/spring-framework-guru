package guru.springframework.spring6restmvc.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import guru.springframework.spring6restmvc.model.Customer;

public interface CustomerService {
  List<Customer> listCustomers();
  Optional<Customer> getCustomerById(UUID id);
  Customer saveNewCustomer(Customer customer);
  void updateCustomerById(UUID id, Customer customer);
  void deleteById(UUID customerId);
  void patchCustomerById(UUID customerId, Customer customer);
}
