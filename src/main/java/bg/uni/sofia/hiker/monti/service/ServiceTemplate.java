package bg.uni.sofia.hiker.monti.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class ServiceTemplate {

    private final RestTemplate restTemplate = new RestTemplate();

    public String getChatResponse(String message) {
        Map<String, Object> requestBody = Map.of(
                "model", "gpt-3.5-turbo",
                "messages", List.of(
                        Map.of("role", "system", "content", "You are a helpful assistant."),
                        Map.of("role", "user", "content", message)
                ),
                "max_tokens", 500
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + "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"); // ✅ Correct way to set API Key

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<Map> responseEntity = restTemplate.exchange("https://api.openai.com/v1/chat/completions", HttpMethod.POST, requestEntity, Map.class);

        List<Map<String, Object>> choices = (List<Map<String, Object>>) responseEntity.getBody().get("choices");
        Map<String, Object> responseContent = (Map<String, Object>) choices.get(0).get("message");

        String content = responseContent.get("content").toString();

        return content;
    }
}
