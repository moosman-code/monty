package bg.uni.sofia.hiker.monti.kafka.consumer;

import java.time.Duration;
import java.util.Collections;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import bg.uni.sofia.hiker.monti.features.Feature;
import bg.uni.sofia.hiker.monti.kafka.serde.JsonDeserializer;
import static bg.uni.sofia.hiker.monti.kafka.topic.TopicName.FEATURES_V1;

public class PeakExtractor {

    public Set<Feature> extract() {

        KafkaConsumer<String, Feature> consumer = getStringPeakKafkaConsumer();

        consumer.subscribe(Collections.singletonList(FEATURES_V1.getValue()));
        Set<Feature> result = new HashSet<>();

        try {
            ConsumerRecords<String, Feature> records = consumer.poll(Duration.ofMillis(10000));
            for (ConsumerRecord<String, Feature> record : records) {
                result.add(record.value());
            }
        } finally {
            consumer.close();
        }

        return result;
    }

    private static KafkaConsumer<String, Feature> getStringPeakKafkaConsumer() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "my-consumer-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class.getName());
        props.put("value.deserializer.type", Feature.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        return new KafkaConsumer<>(props);
    }
}
