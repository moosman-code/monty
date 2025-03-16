package bg.uni.sofia.hiker.monti.peaks;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.kafka.support.serializer.JsonSerde;

import bg.uni.sofia.hiker.monti.kafka.serde.JsonSerializer;
import bg.uni.sofia.hiker.monti.kafka.topic.TopicName;

public class JsonFileToPeak {

    public static final String BOOTSTRAP_SERVER = "localhost:9092";

    public void init() {

        Properties producerProps = getProducerProperties(JsonSerializer.class.getName());
        KafkaProducer<String, Peak> producer = new KafkaProducer<>(producerProps);

        String filePath = "data/output-peaks.json";
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            PeakData peakData = objectMapper.readValue(new File(filePath), PeakData.class);

            List<Peak> peaks = peakData.features();

            for (Peak peak : peaks) {
                System.out.println("Sending: " + peak);
                ProducerRecord<String, Peak> record = new ProducerRecord<>(TopicName.SUGGESTED_PLACES.getValue(), peak.id(), peak);
                producer.send(record);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Properties getProducerProperties(String valueSerializer) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer);

        return props;
    }

}
