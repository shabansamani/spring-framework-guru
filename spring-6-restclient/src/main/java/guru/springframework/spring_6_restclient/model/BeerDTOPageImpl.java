package guru.springframework.spring_6_restclient.model;

import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true, value = "pageable")
public class BeerDTOPageImpl<BeerDTO> extends PageImpl<guru.springframework.spring_6_restclient.model.BeerDTO> {

  @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
  public BeerDTOPageImpl(@JsonProperty("content") List<guru.springframework.spring_6_restclient.model.BeerDTO> content,
      @JsonProperty("number") int page,
      @JsonProperty("size") int size,
      @JsonProperty("totalElements") long total) {
    super(content, PageRequest.of(page, size), total);
  }

  public BeerDTOPageImpl(List<guru.springframework.spring_6_restclient.model.BeerDTO> content, Pageable pageable,
      long total) {
    super(content, pageable, total);
  }

  public BeerDTOPageImpl(List<guru.springframework.spring_6_restclient.model.BeerDTO> content) {
    super(content);
  }
}
