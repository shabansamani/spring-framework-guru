package guru.springframework.spring6restmvc.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import guru.springframework.spring6restmvc.model.BeerDTO;

public interface BeerService {

  List<BeerDTO> listBeers(String beerName);

  Optional<BeerDTO> getBeerById(UUID id);
  BeerDTO saveNewBeer(BeerDTO beer);
  Optional<BeerDTO> updateBeerById(UUID id, BeerDTO beer);
  Boolean deleteById(UUID beerId);
  Optional<BeerDTO> patchBeerById(UUID beerId, BeerDTO beer);
}
