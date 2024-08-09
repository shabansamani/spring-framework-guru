package guru.springframework.spring_ai_intro.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OpenAIServiceImplTest {

    @Autowired
    OpenAIService openAIService;

    @Test
    void testGetAnswer() {
        String answer = openAIService.getAnswer("Tell me a dad joke.");
        System.out.println("Got the answer");
        System.out.println(answer);
    }
}
