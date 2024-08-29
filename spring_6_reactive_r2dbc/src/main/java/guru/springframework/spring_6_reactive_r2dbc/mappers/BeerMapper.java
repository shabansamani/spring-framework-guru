package guru.springframework.spring_6_reactive_r2dbc.mappers;

import org.mapstruct.Mapper;

import guru.springframework.spring_6_reactive_r2dbc.domain.Beer;
import guru.springframework.spring_6_reactive_r2dbc.model.BeerDTO;

@Mapper
public interface BeerMapper {
  Beer beerDTOToBeer(BeerDTO beerDTO);

  BeerDTO beerToBeerDTO(Beer beer);
}
