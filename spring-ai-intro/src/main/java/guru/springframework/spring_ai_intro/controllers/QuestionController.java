package guru.springframework.spring_ai_intro.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.spring_ai_intro.models.Answer;
import guru.springframework.spring_ai_intro.models.GetCapitalRequest;
import guru.springframework.spring_ai_intro.models.GetCapitalResponse;
import guru.springframework.spring_ai_intro.models.GetCapitalWithInfoResponse;
import guru.springframework.spring_ai_intro.models.Question;
import guru.springframework.spring_ai_intro.services.OpenAIService;


@RestController
public class QuestionController {

    private final OpenAIService openAIService;

    public QuestionController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @PostMapping("/capitalWithInfo")
    public GetCapitalWithInfoResponse getCapitalWithInfo(@RequestBody GetCapitalRequest getCapitalRequest) {
        return this.openAIService.getCapitalWithInfo(getCapitalRequest);
    }
    

    @PostMapping("/capital")
    public GetCapitalResponse getCapital(@RequestBody GetCapitalRequest getCapitalRequest) {
        return openAIService.getCapital(getCapitalRequest);
    }
    

    @PostMapping("/ask")
    public Answer askQuestion(@RequestBody Question question) {
        return openAIService.getAnswer(question);
    }
    
    
}
