package guru.springframework.spring_6_reactive_mongo.mappers;

import org.mapstruct.Mapper;

import guru.springframework.spring_6_reactive_mongo.domain.Beer;
import guru.springframework.spring_6_reactive_mongo.model.BeerDTO;

@Mapper
public interface BeerMapper {
    BeerDTO beerToBeerDTO(Beer beer);
    Beer beerDTOToBeer(BeerDTO beerDTO);
}
