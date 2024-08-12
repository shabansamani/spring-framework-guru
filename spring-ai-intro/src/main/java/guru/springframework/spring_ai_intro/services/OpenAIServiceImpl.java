package guru.springframework.spring_ai_intro.services;

import java.util.Map;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.parser.BeanOutputParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import guru.springframework.spring_ai_intro.models.Answer;
import guru.springframework.spring_ai_intro.models.GetCapitalRequest;
import guru.springframework.spring_ai_intro.models.GetCapitalResponse;
import guru.springframework.spring_ai_intro.models.GetCapitalWithInfoResponse;
import guru.springframework.spring_ai_intro.models.Question;

@Service
public class OpenAIServiceImpl implements OpenAIService {

    private final ChatClient chatClient;

    @Autowired
    ObjectMapper objectMapper;

    public OpenAIServiceImpl(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Value("classpath:templates/get-capital-prompt.st")
    private Resource getCapitalPrompt;

    @Value("classpath:templates/get-capital-with-info-prompt.st")
    private Resource getCapitalWithInfoPrompt;

    @Override
    public String getAnswer(String question) {
        PromptTemplate promptTemplate = new PromptTemplate(question);
        Prompt prompt = promptTemplate.create();
        ChatResponse response = chatClient.call(prompt);

        return response.getResult().getOutput().getContent();
    }

    @Override
    public Answer getAnswer(Question question) {
        PromptTemplate promptTemplate = new PromptTemplate(question.question());
        Prompt prompt = promptTemplate.create();
        ChatResponse response = chatClient.call(prompt);

        return new Answer(response.getResult().getOutput().getContent().toString());
    }

    @Override
    public GetCapitalResponse getCapital(GetCapitalRequest getCapitalRequest) {
        BeanOutputParser<GetCapitalResponse> parser = new BeanOutputParser<>(GetCapitalResponse.class);
        String format = parser.getFormat();
        PromptTemplate promptTemplate = new PromptTemplate(getCapitalPrompt);
        Prompt prompt = promptTemplate.create(
            Map.of(
                "stateOrCountry", getCapitalRequest.stateOrCountry(), 
                "format", format
            )
        );
        ChatResponse response = chatClient.call(prompt);

        return parser.parse(response.getResult().getOutput().getContent());
    }

    @Override
    public GetCapitalWithInfoResponse getCapitalWithInfo(GetCapitalRequest getCapitalRequest) {
        BeanOutputParser<GetCapitalWithInfoResponse> parser = new BeanOutputParser<>(GetCapitalWithInfoResponse.class);
        String format = parser.getFormat();
        PromptTemplate promptTemplate = new PromptTemplate(getCapitalWithInfoPrompt);
        Prompt prompt = promptTemplate.create(
            Map.of(
                "stateOrCountry", getCapitalRequest.stateOrCountry(),
                "format", format));
        ChatResponse response = chatClient.call(prompt);

        return parser.parse(response.getResult().getOutput().getContent());
    }
}
