package guru.springframework.spring_6_restclient.client;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import guru.springframework.spring_6_restclient.model.BeerDTO;
import guru.springframework.spring_6_restclient.model.BeerDTOPageImpl;
import guru.springframework.spring_6_restclient.model.BeerStyle;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
public class BeerClientImpl implements BeerClient {

  public static final String GET_BEER_PATH = "/api/v1/beer";
  public static final String GET_BEER_BY_ID_PATH = GET_BEER_PATH + "/{beerId}";

  private final RestClient.Builder restClientBuilder;

  @Override
  public Page<BeerDTO> listBeers() {
    return listBeers(null, null, null, null, null);
  }

  @Override
  public Page<BeerDTO> listBeers(String beerName, BeerStyle beerStyle, Boolean showInventory, Integer pageNumber,
      Integer pageSize) {
    RestClient restClient = restClientBuilder.build();

    UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath(GET_BEER_PATH);

    if (beerName != null)
      uriComponentsBuilder.queryParam("beerName", beerName);
    if (beerStyle != null)
      uriComponentsBuilder.queryParam("beerStyle", beerStyle);
    if (showInventory != null)
      uriComponentsBuilder.queryParam("showInventory", showInventory);
    if (pageNumber != null)
      uriComponentsBuilder.queryParam("pageNumber", pageNumber);
    if (pageSize != null)
      uriComponentsBuilder.queryParam("pageSize", pageSize);

    return restClient.get()
        .uri(uriComponentsBuilder.toUriString())
        .retrieve()
        .body(BeerDTOPageImpl.class);
  }

  @Override
  public BeerDTO getBeerById(UUID beerId) {
    RestClient restClient = restClientBuilder.build();

    return restClient.get()
        .uri(uriBuilder -> uriBuilder.path(GET_BEER_BY_ID_PATH).build(beerId))
        .retrieve()
        .body(BeerDTO.class);
  }

  @Override
  public BeerDTO createBeer(BeerDTO newDto) {
    RestClient restClient = restClientBuilder.build();
    val location = restClient.post()
        .uri(uriBuilder -> uriBuilder.path(GET_BEER_PATH).build())
        .body(newDto)
        .retrieve()
        .toBodilessEntity()
        .getHeaders()
        .getLocation();

    return restClient.get()
        .uri(location.getPath())
        .retrieve()
        .body(BeerDTO.class);
  }

  @Override
  public BeerDTO updateBeer(BeerDTO beerDTO) {
    RestClient restClient = restClientBuilder.build();
    restClient.put()
        .uri(uriBuilder -> uriBuilder.path(GET_BEER_BY_ID_PATH).build(beerDTO.getId()))
        .body(beerDTO)
        .retrieve()
        .toBodilessEntity();

    return getBeerById(beerDTO.getId());
  }

  @Override
  public void deleteBeer(UUID beerId) {
    RestClient restClient = restClientBuilder.build();
    restClient.delete()
        .uri(uriBuilder -> uriBuilder.path(GET_BEER_BY_ID_PATH).build(beerId))
        .retrieve()
        .toBodilessEntity();
  }

}
