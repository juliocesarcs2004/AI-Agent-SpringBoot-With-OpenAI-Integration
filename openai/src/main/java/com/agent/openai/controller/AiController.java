package com.agent.openai.controller;

import com.agent.openai.dto.AiRequest;
import com.agent.openai.dto.AiResponse;
import com.agent.openai.service.AiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AiController {

    private final AiService aiService;

    @PostMapping("/ask")
    public Mono<ResponseEntity<AiResponse>> askAI(@RequestBody AiRequest request) {
        return aiService.getAIResponse(request.getQuestion())
                .map(response -> ResponseEntity.ok(response));
    }
}
