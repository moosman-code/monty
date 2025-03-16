package bg.uni.sofia.hiker.monti.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import bg.uni.sofia.hiker.monti.controller.MontiController;
import bg.uni.sofia.hiker.monti.kafka.topic.TopicInitializer;
import bg.uni.sofia.hiker.monti.peaks.JsonFileToFeature;

@Configuration
public class ApplicationConfig {

    @Bean(initMethod = "init")
    public TopicInitializer topicInitializer() {
        return new TopicInitializer();
    }

    @Bean
    public MontiController montiController() {
        return new MontiController();
    }
}
