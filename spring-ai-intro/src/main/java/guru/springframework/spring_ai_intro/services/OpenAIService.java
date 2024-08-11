package guru.springframework.spring_ai_intro.services;

import guru.springframework.spring_ai_intro.models.Answer;
import guru.springframework.spring_ai_intro.models.GetCapitalRequest;
import guru.springframework.spring_ai_intro.models.Question;

public interface OpenAIService {
    String getAnswer(String question);
    Answer getAnswer(Question question);
    Answer getCapital(GetCapitalRequest getCapitalRequest);
    Answer getCapitalWithInfo(GetCapitalRequest getCapitalRequest);    
}