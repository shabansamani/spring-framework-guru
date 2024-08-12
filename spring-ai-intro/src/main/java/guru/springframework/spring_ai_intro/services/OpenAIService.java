package guru.springframework.spring_ai_intro.services;

import guru.springframework.spring_ai_intro.models.Answer;
import guru.springframework.spring_ai_intro.models.GetCapitalRequest;
import guru.springframework.spring_ai_intro.models.GetCapitalResponse;
import guru.springframework.spring_ai_intro.models.GetCapitalWithInfoResponse;
import guru.springframework.spring_ai_intro.models.Question;

public interface OpenAIService {
    String getAnswer(String question);
    Answer getAnswer(Question question);
    GetCapitalResponse getCapital(GetCapitalRequest getCapitalRequest);
    GetCapitalWithInfoResponse getCapitalWithInfo(GetCapitalRequest getCapitalRequest);    
}