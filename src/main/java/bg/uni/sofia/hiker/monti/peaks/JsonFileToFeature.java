package bg.uni.sofia.hiker.monti.peaks;

import java.io.File;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;

import bg.uni.sofia.hiker.monti.kafka.serde.JsonSerializer;
import bg.uni.sofia.hiker.monti.kafka.topic.TopicName;
import static bg.uni.sofia.hiker.monti.kafka.topic.TopicName.PEAKS_V1;

public class JsonFileToFeature {

    public static final String BOOTSTRAP_SERVER = "kafka:9092";

    public void initTopicData(TopicName topicName) {
        switch (topicName) {
            case PEAKS_V1 -> initPeakData();
        }
    }

    public void initPeakData() {
        Properties producerProps = getProducerProperties(JsonSerializer.class.getName());
        KafkaProducer<String, Peak> producer = new KafkaProducer<>(producerProps);

        String filePath = "data/output-peaks.json";
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            PeakData peakData = objectMapper.readValue(new File(filePath), PeakData.class);

            List<Peak> peaks = peakData.features();

            for (Peak peak : peaks) {
                System.out.println("Sending: " + peak);
                ProducerRecord<String, Peak> record = new ProducerRecord<>(PEAKS_V1.getValue(), peak.id(), peak);
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
