package guru.springframework.spring_6_reactive_r2dbc.bootstrap;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.spring_6_reactive_r2dbc.domain.Beer;
import guru.springframework.spring_6_reactive_r2dbc.domain.Customer;
import guru.springframework.spring_6_reactive_r2dbc.repositories.BeerRepository;
import guru.springframework.spring_6_reactive_r2dbc.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class BootStrapData implements CommandLineRunner {

    private final BeerRepository beerRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
        loadBeerData();
        loadCustomerData();
        beerRepository.count().subscribe(count -> {
            System.out.println("Beer count is: " + count);
        });
        customerRepository.count().subscribe(count -> {
            System.out.println("Customer count is: " + count);
        });
    }

    private void loadBeerData() {

        beerRepository.count().subscribe(count -> {
            if (count == 0) {
                Beer beer1 = Beer.builder()
                        .beerName("Galaxy Cat")
                        .beerStyle("PALE_ALE")
                        .upc("12356")
                        .price(new BigDecimal("12.99"))
                        .quantityOnHand(122)
                        .createdDate(LocalDateTime.now())
                        .lastModifiedDate(LocalDateTime.now())
                        .build();

                Beer beer2 = Beer.builder()
                        .beerName("Crank")
                        .beerStyle("PALE_ALE")
                        .upc("12356222")
                        .price(new BigDecimal("11.99"))
                        .quantityOnHand(392)
                        .createdDate(LocalDateTime.now())
                        .lastModifiedDate(LocalDateTime.now())
                        .build();

                Beer beer3 = Beer.builder()
                        .beerName("Sunshine City")
                        .beerStyle("IPA")
                        .upc("12356")
                        .price(new BigDecimal("13.99"))
                        .quantityOnHand(144)
                        .createdDate(LocalDateTime.now())
                        .lastModifiedDate(LocalDateTime.now())
                        .build();

                beerRepository.save(beer1).subscribe();
                beerRepository.save(beer2).subscribe();
                beerRepository.save(beer3).subscribe();
            }
        });
    }

    private void loadCustomerData() {
        customerRepository.count().subscribe(count -> {
            if (count == 0) {
                Customer customer1 = Customer.builder().customerName("Lawrence Penderson").build();
                Customer customer2 = Customer.builder().customerName("Arin Hanson").build();
                Customer customer3 = Customer.builder().customerName("Dan Avidan").build();

                customerRepository.save(customer1).subscribe();
                customerRepository.save(customer2).subscribe();
                customerRepository.save(customer3).subscribe();
            }
        });
    }

}
