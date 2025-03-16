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

import bg.uni.sofia.hiker.monti.kafka.serde.JsonDeserializer;
import bg.uni.sofia.hiker.monti.peaks.Peak;

public class PeakExtractor {

    public Set<Peak> extract() {

        KafkaConsumer<String, Peak> consumer = getStringPeakKafkaConsumer();

        consumer.subscribe(Collections.singletonList("peaks-v1"));
        Set<Peak> result = new HashSet<>();

        try {
            ConsumerRecords<String, Peak> records = consumer.poll(Duration.ofMillis(10000));
            System.out.println("%n%n%n%n%n%n%n%n%n%s%n%n%n%n%n%n%n%n%n".formatted(records.count()));
            for (ConsumerRecord<String, Peak> record : records) {
                result.add(record.value());
            }
        } finally {
            consumer.close();
        }

        return result;
    }

    private static KafkaConsumer<String, Peak> getStringPeakKafkaConsumer() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "my-consumer-group-" + UUID.randomUUID());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class.getName());
        props.put("value.deserializer.type", Peak.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        return new KafkaConsumer<>(props);
    }
}
