package guru.springframework.spring6restmvc.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import guru.springframework.spring6restmvc.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {
  private Map<UUID, Customer> customerMap;

  public CustomerServiceImpl() {
    this.customerMap = new HashMap<>();

    Customer customer1 = Customer.builder()
    .customerName("Lawrence Penderson")
    .id(UUID.randomUUID())
    .version(1)
    .createdDate(LocalDateTime.now())
    .lastModifiedDate(LocalDateTime.now())
    .build();

    Customer customer2 = Customer.builder()
    .customerName("Dan Avidan")
    .id(UUID.randomUUID())
    .version(1)
    .createdDate(LocalDateTime.now())
    .lastModifiedDate(LocalDateTime.now())
    .build();

    Customer customer3 = Customer.builder()
    .customerName("Arin Hanson")
    .id(UUID.randomUUID())
    .version(1)
    .createdDate(LocalDateTime.now())
    .lastModifiedDate(LocalDateTime.now())
    .build();

    this.customerMap.put(customer1.getId(), customer1);
    this.customerMap.put(customer2.getId(), customer2);
    this.customerMap.put(customer3.getId(), customer3);
  }
  @Override
  public List<Customer> listCustomers() {
    return new ArrayList<>(this.customerMap.values());
  }

  @Override
  public Optional<Customer> getCustomerById(UUID id) {
   return Optional.of(customerMap.get(id)); 
  }
  
  @Override
  public Customer saveNewCustomer(Customer customer) {
    Customer savedCustomer = Customer.builder()
    .customerName(customer.getCustomerName())
    .id(UUID.randomUUID())
    .version(1)
    .createdDate(LocalDateTime.now())
    .lastModifiedDate(LocalDateTime.now())
    .build();

    this.customerMap.put(savedCustomer.getId(), savedCustomer);
    return savedCustomer;
  }

  @Override
  public void updateCustomerById(UUID id, Customer customer) {
    Customer existing = this.customerMap.get(id);
    existing.setCustomerName(customer.getCustomerName());
    existing.setLastModifiedDate(LocalDateTime.now());
    this.customerMap.put(existing.getId(), existing);
  }

  @Override
  public void deleteById(UUID customerId) {
    this.customerMap.remove(customerId);
  }
  
  @Override
  public void patchCustomerById(UUID customerId, Customer customer) {
    Customer existing = customerMap.get(customerId);

    if(StringUtils.hasText(customer.getCustomerName())) existing.setCustomerName(customer.getCustomerName());
  }
}
