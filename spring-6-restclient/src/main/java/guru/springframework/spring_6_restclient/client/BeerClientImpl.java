package guru.springframework.spring_6_restclient.client;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import guru.springframework.spring_6_restclient.model.BeerDTO;
import guru.springframework.spring_6_restclient.model.BeerStyle;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BeerClientImpl implements BeerClient {

  public static final String GET_BEER_PATH = "/api/v1/beer";
  public static final String GET_BEER_BY_ID_PATH = GET_BEER_PATH + "/{beerId}";

  private final RestClient.Builder restClientBuilder;

  @Override
  public Page<BeerDTO> listBeers() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'listBeers'");
  }

  @Override
  public Page<BeerDTO> listBeers(String beerName, BeerStyle beerStyle, Boolean showInventory, Integer pageNumber,
      Integer pageSize) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'listBeers'");
  }

  @Override
  public BeerDTO getBeerById(UUID beerId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getBeerById'");
  }

  @Override
  public BeerDTO createBeer(BeerDTO newDto) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'createBeer'");
  }

  @Override
  public BeerDTO updateBeer(BeerDTO beerDTO) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updateBeer'");
  }

  @Override
  public void deleteBeer(UUID beerId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteBeer'");
  }

}
