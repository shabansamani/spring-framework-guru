package guru.springframework.spring6restmvc.bootstrap;

import guru.springframework.spring6restmvc.entities.Beer;
import guru.springframework.spring6restmvc.entities.Customer;
import guru.springframework.spring6restmvc.model.BeerCSVRecord;
import guru.springframework.spring6restmvc.model.BeerStyle;
import guru.springframework.spring6restmvc.repositories.BeerRepository;
import guru.springframework.spring6restmvc.repositories.CustomerRepository;
import guru.springframework.spring6restmvc.services.BeerCsvService;
import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

	private final BeerRepository beerRepository;
	private final CustomerRepository customerRepository;
	private final BeerCsvService beerCsvService;

	@Transactional
	@Override
	public void run(String... args) throws Exception {
		loadBeerData();
		loadCsvData();
		loadCustomerData();
	}

	private void loadCsvData() throws FileNotFoundException {
		if (beerRepository.count() < 50) {
			File file = ResourceUtils.getFile("classpath:csvdata/beers.csv");
			List<BeerCSVRecord> recs = beerCsvService.convertCSV(file);

			recs.forEach(beerCsvRecord -> {
				BeerStyle beerStyle = switch(beerCsvRecord.getStyle()) {
					case "American Pale Lager" -> BeerStyle.LAGER;
					case "American Pale Ale (APA)", "American Black Ale", "Belgian Dark Ale", "American Blonde Ale" -> BeerStyle.ALE;
					case "American IPA", "American Double / Imperial IPA", "Belgian IPA" -> BeerStyle.IPA;
					case "American Porter" -> BeerStyle.PORTER;
					case "Oatmeal Stout", "American Stout" -> BeerStyle.STOUT;
					case "Saison / Farmhouse Ale" -> BeerStyle.SAISON;
					case "Fruit / Vegetable Beer", "Winter Warmer", "Berliner Weissbier" -> BeerStyle.WHEAT;
					case "English Pale Ale" -> BeerStyle.PALE_ALE;
					default -> BeerStyle.PILSNER;
				};

				beerRepository.save(Beer.builder()
						.beerName(StringUtils.abbreviate(beerCsvRecord.getBeer(), 50))
						.beerStyle(beerStyle)
						.price(BigDecimal.TEN)
						.upc(beerCsvRecord.getRow().toString())
						.quantityOnHand(beerCsvRecord.getCount())
					.build());
			});
		}
	}

	private void loadCustomerData() {
		Customer customer1 = Customer.builder()
				.customerName("Lawrence Penderson")
				.createdDate(LocalDateTime.now())
				.lastModifiedDate(LocalDateTime.now())
				.build();

		Customer customer2 = Customer.builder()
				.customerName("Dan Avidan")
				.createdDate(LocalDateTime.now())
				.lastModifiedDate(LocalDateTime.now())
				.build();

		Customer customer3 = Customer.builder()
				.customerName("Arin Hanson")
				.createdDate(LocalDateTime.now())
				.lastModifiedDate(LocalDateTime.now())
				.build();

		customerRepository.save(customer1);
		customerRepository.save(customer2);
		customerRepository.save(customer3);
	}

	private void loadBeerData() {
		Beer beer1 = Beer.builder()
				.beerName("Galaxy Cat")
				.beerStyle(BeerStyle.PALE_ALE)
				.upc("12356")
				.price(new BigDecimal("12.99"))
				.quantityOnHand(122)
				.createdDate(LocalDateTime.now())
				.updatedDate(LocalDateTime.now())
				.build();

		Beer beer2 = Beer.builder()
				.beerName("Crank")
				.beerStyle(BeerStyle.PALE_ALE)
				.upc("12356222")
				.price(new BigDecimal("11.99"))
				.quantityOnHand(392)
				.createdDate(LocalDateTime.now())
				.updatedDate(LocalDateTime.now())
				.build();

		Beer beer3 = Beer.builder()
				.beerName("Sunshine City")
				.beerStyle(BeerStyle.IPA)
				.upc("12356")
				.price(new BigDecimal("13.99"))
				.quantityOnHand(144)
				.createdDate(LocalDateTime.now())
				.updatedDate(LocalDateTime.now())
				.build();

		beerRepository.save(beer1);
		beerRepository.save(beer2);
		beerRepository.save(beer3);
	}
}
