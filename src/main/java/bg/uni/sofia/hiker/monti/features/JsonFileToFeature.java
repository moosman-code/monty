package bg.uni.sofia.hiker.monti.features;

import java.io.File;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;

import bg.uni.sofia.hiker.monti.kafka.serde.JsonSerializer;
import static bg.uni.sofia.hiker.monti.kafka.topic.TopicName.FEATURES_V1;

public class JsonFileToFeature {

    public static final String BOOTSTRAP_SERVER = "kafka:9092";

    public void initTopicData() {
        Properties producerProps = getProducerProperties(JsonSerializer.class.getName());
        KafkaProducer<String, Feature> producer = new KafkaProducer<>(producerProps);

        String featureFilePath = "data/output-features.json";

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            List<Feature> featureData = objectMapper.readValue(new File(featureFilePath), FeatureData.class).features();

            for (Feature feature : featureData) {
                System.out.println("Sending: " + feature);
                ProducerRecord<String, Feature> record = new ProducerRecord<>(FEATURES_V1.getValue(), feature.id(), feature);
                producer.send(record);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Properties getProducerProperties(String valueSerializer) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer);
        return props;
    }
}
