package bg.uni.sofia.hiker.monti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class ServiceTemplate {

    private Environment environment;

    private final RestTemplate restTemplate = new RestTemplate();

    public ServiceTemplate() {
        // Load Spring Context
        ApplicationContext context = new ClassPathXmlApplicationContext();
        this.environment = context.getEnvironment();
    }

    public String getChatResponse(String message) {
        Map<String, Object> requestBody = Map.of(
                "model", "gpt-3.5-turbo",
                "messages", List.of(
                        Map.of("role", "system", "content", "You are a helpful assistant."),
                        Map.of("role", "user", "content", message)
                ),
                "max_tokens", 500
        );

        String apiKey = environment.getProperty("OPENAI_API_KEY");
        if (apiKey == null) {
            throw new RuntimeException("API Key is missing!");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<Map> responseEntity = restTemplate.exchange("https://api.openai.com/v1/chat/completions", HttpMethod.POST, requestEntity, Map.class);

        List<Map<String, Object>> choices = (List<Map<String, Object>>) responseEntity.getBody().get("choices");
        Map<String, Object> responseContent = (Map<String, Object>) choices.get(0).get("message");

        String content = responseContent.get("content").toString();

        return content;
    }
}
