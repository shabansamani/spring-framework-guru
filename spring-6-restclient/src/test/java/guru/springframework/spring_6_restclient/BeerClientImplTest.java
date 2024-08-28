package guru.springframework.spring_6_restclient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.web.client.HttpClientErrorException;

import guru.springframework.spring_6_restclient.client.BeerClientImpl;
import guru.springframework.spring_6_restclient.model.BeerDTO;
import guru.springframework.spring_6_restclient.model.BeerStyle;

/**
 * BeerClientImplTest
 */
@SpringBootTest
public class BeerClientImplTest {

  @Autowired
  BeerClientImpl beerClient;

  @Test
  void testDeleteBeer() {
    BeerDTO newDto = BeerDTO.builder()
        .price(new BigDecimal("10.99"))
        .beerName("Mango Bobs 2")
        .beerStyle(BeerStyle.IPA)
        .quantityOnHand(500)
        .upc("123245")
        .build();

    BeerDTO beerDto = beerClient.createBeer(newDto);

    beerClient.deleteBeer(beerDto.getId());

    assertThrows(HttpClientErrorException.class, () -> {
      // should error
      beerClient.getBeerById(beerDto.getId());
    });
  }

  @Test
  void testUpdateBeer() {

    BeerDTO newDto = BeerDTO.builder()
        .price(new BigDecimal("10.99"))
        .beerName("Mango Bobs 2")
        .beerStyle(BeerStyle.IPA)
        .quantityOnHand(500)
        .upc("123245")
        .build();

    BeerDTO beerDto = beerClient.createBeer(newDto);

    final String newName = "Mango Bobs 3";
    beerDto.setBeerName(newName);
    BeerDTO updatedBeer = beerClient.updateBeer(beerDto);

    assertEquals(newName, updatedBeer.getBeerName());
  }

  @Test
  void testCreateBeer() {

    BeerDTO newDto = BeerDTO.builder()
        .price(new BigDecimal("10.99"))
        .beerName("Mango Bobs")
        .beerStyle(BeerStyle.IPA)
        .quantityOnHand(500)
        .upc("123245")
        .build();

    BeerDTO savedDto = beerClient.createBeer(newDto);
    assertNotNull(savedDto);
  }

  @Test
  void getBeerById() {

    Page<BeerDTO> beerDTOS = beerClient.listBeers();

    BeerDTO dto = beerDTOS.getContent().getFirst();

    BeerDTO byId = beerClient.getBeerById(dto.getId());

    assertNotNull(byId);
  }

  @Test
  void listBeersNoBeerName() {

    beerClient.listBeers(null, null, null, null, null);
  }

  @Test
  void listBeers() {

    beerClient.listBeers("ALE", null, null, null, null);
  }
}
