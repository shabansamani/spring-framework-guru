package guru.springframework.spring6webclient.client;

import static org.awaitility.Awaitility.await;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import guru.springframework.spring6webclient.model.BeerDTO;

@SpringBootTest
public class BeerClientImplTest {

    @Autowired
    BeerClient beerClient;

    @Test
    void listBeer() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        beerClient.listBeer().subscribe(response -> {
            System.out.println(response);
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void listBeerMap() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        beerClient.listBeerMap().subscribe(response -> {
            System.out.println(response);
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void listBeerJsonNode() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        beerClient.listBeerJsonNode().subscribe(jsonNode -> {
            System.out.println(jsonNode.toPrettyString());
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void listBeerDto() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        beerClient.listBeerDtos().subscribe(dto -> {
            System.out.println(dto.getBeerName());
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void testGetBeerById() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        beerClient.listBeerDtos().flatMap(dto -> beerClient.getBeerById(dto.getId()))
        .subscribe(byIdDto -> {
            System.out.println(byIdDto.getBeerName());
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void testGetBeerByBeerStyle() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        beerClient.getBeerByBeerStyle("Pale Ale")
                .subscribe(dto -> {
                    System.out.println(dto.toString());
                    atomicBoolean.set(true);
                });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void testCreateBeer() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        BeerDTO newDto = BeerDTO.builder()
                .price(new BigDecimal("10.99"))
                .beerName("Mango Bobs")
                .beerStyle("IPA")
                .quantityOnHand(500)
                .upc("12345678")
                .build();

        beerClient.createBeer(newDto)
                .subscribe(dto -> {
                    System.out.println(dto.toString());
                    atomicBoolean.set(true);
                });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void testUpdateBeer() {
        final String NAME = "New Name PUT";

        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        beerClient.listBeerDtos()
            .next()
            .doOnNext(beerDto -> beerDto.setBeerName(NAME))
            .flatMap(dto -> beerClient.updateBeer(dto))
            .subscribe(byIdDto -> {
                System.out.println(byIdDto.toString());
                atomicBoolean.set(true);
            });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void testDeleteBeer() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        beerClient.listBeerDtos()
            .next()
            .flatMap(beerDto -> beerClient.deleteBeer(beerDto))
            .doOnSuccess(onSuccess -> atomicBoolean.set(true))
            .subscribe();

        await().untilTrue(atomicBoolean);
    }

    @Test
    void testPatchBeer() {
        final String NAME = "New Name PATCH";

        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        beerClient.listBeerDtos()
            .next()
            .map(beerDto -> BeerDTO.builder().beerName(NAME).id(beerDto.getId()).build())
            .flatMap(dto -> beerClient.patchBeer(dto))
            .subscribe(byIdDto -> {
                System.out.println(byIdDto.toString());
                atomicBoolean.set(true);
            });

        await().untilTrue(atomicBoolean);
    }
}
