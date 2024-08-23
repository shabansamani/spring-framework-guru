package guru.springframework.spring_6_restclient.client;

import java.util.UUID;

import org.springframework.data.domain.Page;

import guru.springframework.spring_6_restclient.model.BeerDTO;
import guru.springframework.spring_6_restclient.model.BeerStyle;

public interface BeerClient {
  Page<BeerDTO> listBeers();

  Page<BeerDTO> listBeers(String beerName, BeerStyle beerStyle, Boolean showInventory, Integer pageNumber,
      Integer pageSize);

  BeerDTO getBeerById(UUID beerId);

  BeerDTO createBeer(BeerDTO newDto);

  BeerDTO updateBeer(BeerDTO beerDTO);

  void deleteBeer(UUID beerId);
}
