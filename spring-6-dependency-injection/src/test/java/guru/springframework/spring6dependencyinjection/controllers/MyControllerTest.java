package guru.springframework.spring6dependencyinjection.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("uat")
@SpringBootTest
class MyControllerTest {

    @Autowired
    MyController myController;

    @Test
    void printData() {
        System.out.println(myController.printData());
    }

}