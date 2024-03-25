package guru.springframework.spring6dependencyinjection.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("uat")
@Service
public class UATService implements DataService {
    @Override
    public String getData() {
        return "UAT Data source";
    }
}
