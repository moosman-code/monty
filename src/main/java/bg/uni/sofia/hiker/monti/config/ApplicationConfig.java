package bg.uni.sofia.hiker.monti.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import bg.uni.sofia.hiker.monti.kafka.TopicInitializer;

@Configuration
public class ApplicationConfig {

    @Bean(initMethod = "init")
    public TopicInitializer topicInitializer() {
        return new TopicInitializer();
    }
}
