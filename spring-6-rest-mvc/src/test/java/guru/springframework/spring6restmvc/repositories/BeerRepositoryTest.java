package guru.springframework.spring6restmvc.repositories;

import guru.springframework.spring6restmvc.entities.Beer;
import guru.springframework.spring6restmvc.model.BeerStyle;
import jakarta.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

@DataJpaTest
class BeerRepositoryTest {
    @Autowired
    BeerRepository beerRepository;

    @Test
    void testSaveBeer() {
        Beer savedBeer = beerRepository.save(Beer.builder()
                            .beerName("My Beer")
                            .beerStyle(BeerStyle.PALE_ALE)
                            .upc("8193809830")
                            .price(new BigDecimal("11.99")).build());

        beerRepository.flush();
        assertThat(savedBeer).isNotNull();
        assertThat(savedBeer.getId()).isNotNull();
    }

    @Test
    void testSaveBeerNameTooLong() {

        assertThrows(ConstraintViolationException.class, () -> {
            Beer savedBeer = beerRepository.save(Beer.builder()
            .beerName("My Beer 8320498304802840238402384082098402834u83402840820480384082048")
            .beerStyle(BeerStyle.PALE_ALE)
            .upc("8193809830")
            .price(new BigDecimal("11.99")).build());

            beerRepository.flush();
            assertThat(savedBeer).isNotNull();
            assertThat(savedBeer.getId()).isNotNull();
        });
    }
}