package com.agent.openai.service;

import com.agent.openai.dto.AiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AiService {

    private final WebClient webClient;

    @Value("${openai.api.key}")
    private String apiKey;

    public Mono<AiResponse> getAIResponse(String userQuestion) {
        log.info("Sending user question to OpenAI API: {}", userQuestion);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-3.5-turbo");

        Map<String, String> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", userQuestion);

        requestBody.put("messages", new Object[]{message});

        return webClient.post()
                .uri("https://api.openai.com/v1/chat/completions")
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .map(responseBody -> new AiResponse("OpenAI response: " + responseBody))

                .doOnError(error -> log.error("Error calling OpenAI API", error))
                .onErrorResume(error -> Mono.just(new AiResponse("Error calling OpenAI API: " + error.getMessage())));
    }
}
