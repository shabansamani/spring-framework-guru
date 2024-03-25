package guru.springframework.spring6dependencyinjection.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile({"dev", "default"})
@Service
public class DevService implements DataService{

    @Override
    public String getData() {
        return "Dev Data Source";
    }
}
