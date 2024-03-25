package guru.springframework.spring6dependencyinjection.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("qa")
@Service
public class QAService implements DataService {

    @Override
    public String getData() {
        return "QA Data Source";
    }
}
