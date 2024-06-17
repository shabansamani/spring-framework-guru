package guru.springframework.spring_6_reactive_r2dbc.services;

import org.springframework.stereotype.Service;

import guru.springframework.spring_6_reactive_r2dbc.mappers.BeerMapper;
import guru.springframework.spring_6_reactive_r2dbc.model.BeerDTO;
import guru.springframework.spring_6_reactive_r2dbc.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public Flux<BeerDTO> listBeers() {
        return beerRepository.findAll()
                .map(beerMapper::beerToBeerDTO);
    }

    @Override
    public Mono<BeerDTO> getBeerById(Integer beerId) {
        return beerRepository.findById(beerId)
                .map(beerMapper::beerToBeerDTO);
    }

    @Override
    public Mono<BeerDTO> saveNewBeer(BeerDTO beerDTO) {
        return beerRepository.save(beerMapper.beerDTOToBeer(beerDTO))
                .map(beerMapper::beerToBeerDTO);
    }

    @Override
    public Mono<BeerDTO> updateBeer(Integer beerId, BeerDTO beerDTO) {
        return beerRepository.findById(beerId)
                .map(foundBeer -> {
                    foundBeer.setBeerName(beerDTO.getBeerName());
                    foundBeer.setBeerStyle(beerDTO.getBeerStyle());
                    foundBeer.setPrice(beerDTO.getPrice());
                    foundBeer.setUpc(beerDTO.getUpc());
                    foundBeer.setQuantityOnHand(beerDTO.getQuantityOnHand());

                    return foundBeer;
                }).flatMap(beerRepository::save)
                .map(beerMapper::beerToBeerDTO);
    }

    @Override
    public Mono<BeerDTO> patchBeer(Integer beerId, BeerDTO beerDTO) {
        return beerRepository.findById(beerId)
                .map(foundBeer -> {
                    if (beerDTO.getBeerName() != null)
                        foundBeer.setBeerName(beerDTO.getBeerName());
                    if (beerDTO.getBeerStyle() != null)
                        foundBeer.setBeerStyle(beerDTO.getBeerStyle());
                    if (beerDTO.getPrice() != null)
                        foundBeer.setPrice(beerDTO.getPrice());
                    if (beerDTO.getUpc() != null)
                        foundBeer.setUpc(beerDTO.getUpc());
                    if (beerDTO.getQuantityOnHand() != null)
                        foundBeer.setQuantityOnHand(beerDTO.getQuantityOnHand());

                    return foundBeer;
                }).flatMap(beerRepository::save)
                .map(beerMapper::beerToBeerDTO);
    }

    @Override
    public Mono<Void> deleteBeer(Integer beerId) {
        return beerRepository.deleteById(beerId);

    }

}
