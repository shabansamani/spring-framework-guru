package guru.springframework.spring6dependencyinjection.controllers;

import guru.springframework.spring6dependencyinjection.services.DataService;
import org.springframework.stereotype.Controller;

@Controller
public class MyController {

    private final DataService dataService;

    public MyController(DataService dataService) {
        this.dataService = dataService;
    }

    public String printData() {
        return dataService.getData();
    }
}
