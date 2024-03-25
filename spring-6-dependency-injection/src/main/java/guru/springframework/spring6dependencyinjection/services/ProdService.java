package guru.springframework.spring6dependencyinjection.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("prod")
@Service
public class ProdService implements DataService {

    @Override
    public String getData() {
        return "Production data source";
    }
}
